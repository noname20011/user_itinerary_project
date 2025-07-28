package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.domain.entity.VisitedPhoto;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoRequestDTO;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoResponseDTO;
import domain.travel.travel_itinerary.dto.whitelist.WhiteListRequestDTO;
import domain.travel.travel_itinerary.dto.whitelist.WhiteListResponseDTO;

import java.util.List;
import java.util.UUID;

public interface WhiteListService {
    List<WhiteListResponseDTO> getAllWhiteListByUser(UUID userId);
    WhiteListResponseDTO getDetailWhiteList(UUID whiteListId);

    List<WhiteListResponseDTO> addWhiteLists(List<WhiteListRequestDTO> requestDTOs);
    WhiteListResponseDTO addWhiteList(WhiteListRequestDTO requestDTO);


    void deleteWhiteLists(List<UUID> ids);
    void deleteWhiteList(UUID id);
}
