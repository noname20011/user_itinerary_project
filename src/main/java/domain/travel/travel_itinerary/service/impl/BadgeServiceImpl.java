package domain.travel.travel_itinerary.service.impl;

import domain.travel.travel_itinerary.domain.entity.Badge;
import domain.travel.travel_itinerary.domain.entity.Challenge;
import domain.travel.travel_itinerary.domain.entity.ChallengeOfBadge;
import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import domain.travel.travel_itinerary.dto.badge.BadgeResponseDTO;
import domain.travel.travel_itinerary.dto.challenge.ChallengeRequestDTO;
import domain.travel.travel_itinerary.mapper.BadgeMapper;
import domain.travel.travel_itinerary.mapper.ChallengeMapper;
import domain.travel.travel_itinerary.repository.BadgeRepository;
import domain.travel.travel_itinerary.repository.ChallengeOfBadgeRepository;
import domain.travel.travel_itinerary.repository.ChallengeRepository;
import domain.travel.travel_itinerary.repository.UserRepository;
import domain.travel.travel_itinerary.repository.custom_repository.PagingResult;
import domain.travel.travel_itinerary.service.BadgeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;
    private final ChallengeOfBadgeRepository challengeOfBadgeRepository;
    private final ChallengeRepository challengeRepository;

    private final BadgeMapper badgeMapper;
    private final ChallengeMapper challengeMapper;

    @Override
    public PagingResult<BadgeResponseDTO> getAllBadges(Pageable pageable) {
        Pageable pageFormat = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        Page<Badge> result = badgeRepository.findAll(pageFormat);
        List<BadgeResponseDTO> mapDto = badgeMapper.mapToListResponseDtos(result.stream().toList());
        return new PagingResult<>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                result.getTotalElements(),
                mapDto
        );
    }

    @Override
    public PagingResult<BadgeResponseDTO> getAllBadgesByUser(UUID userId, Pageable pageable) {
        User user = userRepository.findByIdOrThrow(userId);
        return badgeRepository.getBadgesHasUserData(user.getId(), pageable);
    }

    @Override
    public PagingResult<BadgeResponseDTO> getAllBadgesByUserAndTypeBadge(UUID userId, UUID typeBadgeId, Pageable pageable) {
        Pageable pageFormat = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        PagingResult<BadgeResponseDTO> result = badgeRepository.getBadgesHasUserByTypeBadgeId(userId, typeBadgeId, pageFormat);
        return new PagingResult<>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                result.getTotal(),
                result.getData()
        );
    }

    @Override
    public PagingResult<BadgeResponseDTO> getAllBadgesByTypeBadge(UUID typeBadgeId, Pageable pageable) {
        Page<Badge> result = badgeRepository.findAllByTypeBadgeId(typeBadgeId, pageable);
        return new PagingResult<>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                result.getTotalElements(),
                badgeMapper.mapToListResponseDtos(result.stream().toList())
        );
    }

    @Override
    public BadgeResponseDTO getDetailBadgeForUser(UUID userId, UUID badgeId) {
         return badgeRepository.getBadgeByIdForUser(badgeId, userId);
    }

    @Override
    public BadgeResponseDTO getBadge(UUID badgeId) {
        badgeRepository.findByIdOrThrow(badgeId);
        return badgeRepository.getBadgeById(badgeId);
    }

    @Transactional
    @Override
    public BadgeResponseDTO addBadge(BadgeRequestDTO requestDTO) {
        Badge badgeEntity = badgeMapper.mapToEntity(requestDTO);
        Badge badgeSaved = badgeRepository.save(badgeEntity);

//        Check if challenge available, add to ChallengeOfBadge
        List<UUID> challengeIds = requestDTO.getChallengesId();
        if (challengeIds != null && !challengeIds.isEmpty()) {
//          Get Challenges from DB
            List<Challenge> challenges = challengeRepository.findAllById(challengeIds);
//            Init entity ChallengeOfBadge and Save to DB
            linkChallengesToBadge(badgeSaved, challenges);
        }

//        Check if challenges are new data, add to Challenge, then add to ChallengeOfBadge
        List<ChallengeRequestDTO> challengeDTOs = requestDTO.getChallenges();
        if (challengeDTOs != null && !challengeDTOs.isEmpty()) {
            addNewChallengeToBadge(challengeDTOs, badgeSaved);
        }

        return badgeRepository.getBadgeById(badgeSaved.getId());
    }

    @Transactional
    @Override
    public BadgeResponseDTO updateBadge(UUID badgeId, BadgeRequestDTO requestDTO) {
//      Check already exist badge
        badgeRepository.findByIdOrThrow(badgeId);

        Badge badgeEntity = badgeMapper.mapToEntity(requestDTO);
        Badge badgeSaved = badgeRepository.update(badgeId, badgeEntity);

//        Check if challenge available, add to ChallengeOfBadge
        List<UUID> challengeRequestsIds = requestDTO.getChallengesId();
        if (challengeRequestsIds != null && !challengeRequestsIds.isEmpty()) {

//          Get Challenges from DB
            List<UUID> challengesId = challengeOfBadgeRepository.findAllChallengeIdsByBadgeId(badgeId);

//            Delete Challenge had not after update
            Set<UUID> challengeBeenDeleted = challengesId.stream()
                    .filter(challengeId -> !challengeRequestsIds.contains(challengeId))
                    .collect(Collectors.toSet());

            challengeOfBadgeRepository.deleteByBadgeIdAndChallengeIdIn(badgeId, challengeBeenDeleted);

//            Add Challenge to Badge after update
            Set<UUID> challengeBeenAdded = challengeRequestsIds.stream()
                    .filter(challengeId -> !challengesId.contains(challengeId))
                    .collect(Collectors.toSet());

//            Init entity ChallengeOfBadge and Save to DB
            List<Challenge> entityChallengeAdded = challengeRepository.findAllById(challengeBeenAdded);
            linkChallengesToBadge(badgeSaved, entityChallengeAdded);
        }

//        Check if challenges are new data, add to Challenge, then add to ChallengeOfBadge
        List<ChallengeRequestDTO> challengeDTOs = requestDTO.getChallenges();
        if (challengeDTOs != null && !challengeDTOs.isEmpty()) {
            addNewChallengeToBadge(challengeDTOs, badgeSaved);
        }

        return badgeRepository.getBadgeById(badgeSaved.getId());
    }

    @Override
    public void deleteBadge(UUID badgeId) {
        badgeRepository.deleteById(badgeId);
    }

    private void addNewChallengeToBadge(List<ChallengeRequestDTO> requestDTO, Badge badgeSaved) {
//            Mapping from requestDTO to Entity
        List<Challenge> toEntities = requestDTO.stream().map(challengeMapper::mapToEntity).toList();
//            Save Challenge to DB
        List<Challenge> challengesSaved = challengeRepository.saveAll(toEntities);
//            Init entity ChallengeOfBadge and Save to DB
        linkChallengesToBadge(badgeSaved, challengesSaved);
    }

    private void linkChallengesToBadge(Badge badge, List<Challenge> challenges) {
//            Init entity ChallengeOfBadge
        List<ChallengeOfBadge> links = challenges.stream()
                .map(challenge -> new ChallengeOfBadge(badge, challenge))
                .toList();
//            Save to DB
        challengeOfBadgeRepository.saveAll(links);
    }
}
