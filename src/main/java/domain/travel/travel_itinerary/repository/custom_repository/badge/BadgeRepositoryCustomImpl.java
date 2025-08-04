package domain.travel.travel_itinerary.repository.custom_repository.badge;

import domain.travel.travel_itinerary.domain.entity.Badge;
import domain.travel.travel_itinerary.domain.entity.Challenge;
import domain.travel.travel_itinerary.domain.entity.ChallengeOfBadge;
import domain.travel.travel_itinerary.domain.entity.UserChallengeStatus;
import domain.travel.travel_itinerary.domain.enums.StatusProgressEnum;
import domain.travel.travel_itinerary.dto.badge.BadgeResponseDTO;
import domain.travel.travel_itinerary.dto.challenge.ChallengeResponseDTO;
import domain.travel.travel_itinerary.exception.NotFoundException;
import domain.travel.travel_itinerary.mapper.BadgeMapper;
import domain.travel.travel_itinerary.mapper.ChallengeMapper;
import domain.travel.travel_itinerary.repository.custom_repository.PagingResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BadgeRepositoryCustomImpl implements BadgeRepositoryCustom {

    private final BadgeMapper badgeMapper;
    private final ChallengeMapper challengeMapper;

    @PersistenceContext
    EntityManager em;

    @Override
    public PagingResult<BadgeResponseDTO> getBadgesHasUserData(UUID userId, Pageable pageable) {


//        init criteria builder
        CriteriaBuilder cb = em.getCriteriaBuilder();
//        input equivalent select...
        CriteriaQuery<BadgeResponseDTO> query = cb.createQuery(BadgeResponseDTO.class);

//          equivalent from ...
        Root<Badge> badge = query.from(Badge.class);

//      Badge Join ChallengeOfBadge on Badge.id ChallengeOfBadge.badge_id
        Join<Badge, ChallengeOfBadge> challengeOfBadgeJoin = badge.join("challengeOfBadges", JoinType.LEFT);

//      Challenge Join ChallengeOfBadge on Challenge.id ChallengeOfBadge.challenge_id
        Join<ChallengeOfBadge, Challenge> detailChallenge = challengeOfBadgeJoin.join("challenge", JoinType.LEFT);

//        Get total Challenge by badge
        Expression<Long> totalChallenge = cb.countDistinct(detailChallenge.get("id"));

//      Challenge Join UserChallengeStatus on Challenge.id UserChallengeStatus.challenge_id
        Join<Challenge, UserChallengeStatus> userCompletedChallenge = detailChallenge.join("userChallengeStatuses", JoinType.LEFT);

//        where Challenge by user in progress
        userCompletedChallenge.on(cb.equal(userCompletedChallenge.get("user").get("id"), userId));

//          Get total challenge by user completed
        Expression<Long> totalChallengeUserCompleted = cb.countDistinct(userCompletedChallenge.get("challenge").get("id"));

        query.select(cb.construct(BadgeResponseDTO.class,
                badge.get("id"),
                badge.get("name"),
                badge.get("thumbnail"),
                totalChallenge,
                totalChallengeUserCompleted,
                badge.get("status").get("name")
        )).groupBy(badge.get("id"));

//        Pageable
        TypedQuery<BadgeResponseDTO> result = em.createQuery(query);
        result.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        result.setMaxResults(pageable.getPageSize());

//        Get total
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Badge> root = countQuery.from(Badge.class);
        countQuery.select(cb.count(root));
        Long total = em.createQuery(countQuery).getSingleResult();

        return new PagingResult<>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                total,
                result.getResultList()
        );
    }

    @Override
    public PagingResult<BadgeResponseDTO> getBadgesHasUserByTypeBadgeId(UUID userId, UUID typeBadgeId, Pageable pageable) {
        //        init criteria builder
        CriteriaBuilder cb = em.getCriteriaBuilder();
//        input equivalent select...
        CriteriaQuery<BadgeResponseDTO> query = cb.createQuery(BadgeResponseDTO.class);

//          equivalent from ...
        Root<Badge> badge = query.from(Badge.class);

//      Badge Join ChallengeOfBadge on Badge.id ChallengeOfBadge.badge_id
        Join<Badge, ChallengeOfBadge> challengeOfBadgeJoin = badge.join("challengeOfBadges", JoinType.LEFT);

//      Challenge Join ChallengeOfBadge on Challenge.id ChallengeOfBadge.challenge_id
        Join<ChallengeOfBadge, Challenge> detailChallenge = challengeOfBadgeJoin.join("challenge", JoinType.LEFT);

//        Get total Challenge by badge
        Expression<Long> totalChallenge = cb.countDistinct(detailChallenge.get("id"));

//      Challenge Join UserChallengeStatus on Challenge.id UserChallengeStatus.challenge_id
        Join<Challenge, UserChallengeStatus> userCompletedChallenge = detailChallenge.join("userChallengeStatuses", JoinType.LEFT);

//        where Challenge by user in progress and by type badge
        userCompletedChallenge.on(cb.equal(userCompletedChallenge.get("user").get("id"), userId));
        query.where(cb.equal(badge.get("typeBadge").get("id"), typeBadgeId));

//          Get total challenge by user completed
        Expression<Long> totalChallengeUserCompleted = cb.countDistinct(userCompletedChallenge.get("challenge").get("id"));

        query.select(cb.construct(BadgeResponseDTO.class,
                badge.get("id"),
                badge.get("name"),
                badge.get("thumbnail"),
                totalChallenge,
                totalChallengeUserCompleted,
                badge.get("status").get("name")
        )).groupBy(badge.get("id"));

//        Pageable
        TypedQuery<BadgeResponseDTO> result = em.createQuery(query);
        result.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        result.setMaxResults(pageable.getPageSize());

//        Get total badge by type badge
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Badge> root = countQuery.from(Badge.class);
        countQuery.where(cb.equal(badge.get("typeBadge").get("id"), typeBadgeId));
        countQuery.select(cb.count(root));
        Long total = em.createQuery(countQuery).getSingleResult();

        return new PagingResult<>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                total,
                result.getResultList()
        );
    }

    @Transactional
    @Override
    public BadgeResponseDTO getBadgeById(UUID badgeId) {
//        Selecting required fields of Badge
        Badge badge = checkExistsBadge(badgeId);

//      Selecting required fields  of Challenge
        List<Challenge> challenges =
                em.createQuery("select cb.challenge from ChallengeOfBadge cb " +
                                "where cb.badge.id = :id", Challenge.class)
                        .setParameter("id", badgeId)
                        .getResultList();

        BadgeResponseDTO toDto = badgeMapper.mapToResponseDto(badge);
        toDto.setChallenges(challengeMapper.mapToListResponseDtos(challenges));
        return toDto;
    }

    @Transactional
    @Override
    public BadgeResponseDTO getBadgeByIdForUser(UUID badgeId, UUID userId) {
        checkExistsBadge(badgeId);

        Badge badge = em.createQuery("select b from Badge b " +
                        "join fetch b.challengeOfBadges cb " +
                        "join fetch cb.challenge c " +
                        "left join fetch c.userChallengeStatuses ucs " +
                        "where b.id =: badgeId and (ucs.user.id =: userId or ucs.user.id is null)", Badge.class)
                .setParameter("badgeId", badgeId)
                .setParameter("userId", userId)
                .getSingleResult();

//        Mapping field status on challenge
        Map<UUID, StatusProgressEnum> statusChallengeMap = badge.getChallengeOfBadges()
                .stream().map(ChallengeOfBadge::getChallenge)
                .flatMap(challenge ->
                        challenge.getUserChallengeStatuses().stream()
                                .filter(usc -> usc.getUser().getId().equals(userId))
                ).collect(Collectors.toMap(
                        usc ->
                                usc.getChallenge().getId(),
                        UserChallengeStatus::getStatus
                ));

//        Response to ChallengeDTO
        List<ChallengeResponseDTO> challengeDTOS = badge.getChallengeOfBadges().stream()
                .map(ChallengeOfBadge::getChallenge)
                .map(challenge -> {
                    ChallengeResponseDTO dto = challengeMapper.mapToResponseDto(challenge);
                    dto.setStatus(statusChallengeMap.getOrDefault(challenge.getId(), null));
                    return dto;
                }).toList();

//        Mapping to Badge response DTO
        BadgeResponseDTO toBadgeDTO = badgeMapper.mapToResponseDto(badge);
        toBadgeDTO.setChallenges(challengeDTOS);
        toBadgeDTO.setTotalChallenges(badge.getChallengeOfBadges().size());
        toBadgeDTO.setTotalChallengesUserDoing(statusChallengeMap.size());

        return toBadgeDTO;
    }


//    Reuse function
    private Badge checkExistsBadge (UUID badgeId) {
        Badge badge = em.find(Badge.class, badgeId);
        if (badge == null) {
            throw new NotFoundException("Not found Badge with id: " + badgeId);
        }
        return  badge;
    }
}
