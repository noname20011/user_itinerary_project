package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.dto.visited.VisitedRequestDTO;
import domain.travel.travel_itinerary.dto.visited.VisitedResponseDTO;
import domain.travel.travel_itinerary.repository.custom_repository.province.PagingResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface VisitedService {
    PagingResult<VisitedResponseDTO> getAllVisitedByUserId(UUID userId, Pageable pageable);
    VisitedResponseDTO getVisited(UUID visitedId);

//    List<VisitedResponseDTO> addListVisited(List<VisitedRequestDTO> requestDTOS);
    VisitedResponseDTO addVisited(VisitedRequestDTO requestDTO);

//    List<VisitedResponseDTO> updateListVisited(UUID visitedId, List<VisitedRequestDTO> requestDTOS);
    VisitedResponseDTO updateVisited(UUID visitedId, VisitedRequestDTO requestDTO);

    void deleteVisited(UUID visitedId);
    void deleteListVisited(List<UUID> visitedIds);
}
