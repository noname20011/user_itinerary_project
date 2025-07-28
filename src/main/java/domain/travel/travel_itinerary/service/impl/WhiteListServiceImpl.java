package domain.travel.travel_itinerary.service.impl;

import domain.travel.travel_itinerary.config.Translator;
import domain.travel.travel_itinerary.domain.entity.WhiteList;
import domain.travel.travel_itinerary.dto.whitelist.WhiteListRequestDTO;
import domain.travel.travel_itinerary.dto.whitelist.WhiteListResponseDTO;
import domain.travel.travel_itinerary.exception.BusinessException;
import domain.travel.travel_itinerary.exception.NotFoundException;
import domain.travel.travel_itinerary.mapper.WhiteListMapper;
import domain.travel.travel_itinerary.repository.UserRepository;
import domain.travel.travel_itinerary.repository.WhiteListRepository;
import domain.travel.travel_itinerary.service.WhiteListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WhiteListServiceImpl implements WhiteListService {

    private final WhiteListRepository whiteListRepository;
    private final WhiteListMapper whiteListMapper;
    private final UserRepository userRepository;

    @Override
    public List<WhiteListResponseDTO> getAllWhiteListByUser(UUID userId) {
        try {
            List<WhiteList> response = whiteListRepository.getAllByUserIdOrderByCreatedAtDesc(userId);

            return whiteListMapper.mapToListResponseDtos(response);
        } catch (RuntimeException ex) {
            throw new BusinessException(Translator.toLocale("whitelist.get.all.fail"));
        }
    }

    @Override
    public WhiteListResponseDTO getDetailWhiteList(UUID whiteListId) {
        WhiteList response = whiteListRepository
                .findByIdOrThrowWithMessage(
                        whiteListId,
                        Translator.toLocale("whitelist.name")
                );
        return whiteListMapper.mapToResponseDto(response);
    }

    @Transactional
    @Override
    public List<WhiteListResponseDTO> addWhiteLists(List<WhiteListRequestDTO> requestDTOs) {

        if (requestDTOs == null || requestDTOs.isEmpty()) {
            return Collections.emptyList();
        }

//        Check find user
        userRepository.findByIdOrThrow(requestDTOs.get(0).getUserId());

//        Get list destinationId by userId
        Set<UUID> whiteListAvailable =
                whiteListRepository.getAllDestinationIdByUserId(requestDTOs.get(0).getUserId());

//        Filter list dont have in whitelist
        List<WhiteListRequestDTO> filterListToAdd = requestDTOs.stream()
                .filter(request -> !whiteListAvailable.contains(request.getDestinationId()))
                .toList();

//        Save new list to db
        List<WhiteList> entitiesConvert = whiteListMapper.mapToListEntities(filterListToAdd);
        List<WhiteList> response = whiteListRepository.saveAll(entitiesConvert);
        return whiteListMapper.mapToListResponseDtos(response);
    }


    @Transactional
    @Override
    public WhiteListResponseDTO addWhiteList(WhiteListRequestDTO requestDTO) {
        UUID userId = requestDTO.getUserId();
        UUID destinationId = requestDTO.getDestinationId();

//        Check find user
        userRepository.findByIdOrThrow(requestDTO.getUserId());

//      Check if available destination to delete
        if (whiteListRepository.existsByUserIdAndDestinationId(requestDTO.getUserId(), requestDTO.getDestinationId())) {
            whiteListRepository.deleteByDestinationIdAndUserId(destinationId, userId);
        }

//        Save new record
        WhiteList entityConvert = whiteListMapper.mapToEntity(requestDTO);
        return whiteListMapper.mapToResponseDto(whiteListRepository.save(entityConvert));

    }

    @Transactional
    @Override
    public void deleteWhiteLists(List<UUID> ids) {
        whiteListRepository.deleteAllById(ids);
    }

    @Override
    public void deleteWhiteList(UUID id) {
        whiteListRepository.deleteById(id);
    }
}
