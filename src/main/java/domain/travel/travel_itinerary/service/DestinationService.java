package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import domain.travel.travel_itinerary.dto.destination.DestinationResponseDTO;
import domain.travel.travel_itinerary.repository.custom_repository.PagingResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DestinationService {

    PagingResult<DestinationResponseDTO> getAllDestinationByProvince(UUID provinceId, Pageable pageable);
    DestinationResponseDTO getDestination(UUID destinationId);

    List<DestinationResponseDTO> addDestinations(List<DestinationRequestDTO> destinationRequestDTOS);
    DestinationResponseDTO addDestination(DestinationRequestDTO destinationRequestDTO);

    List<DestinationResponseDTO> updateDestinations(UUID destinationId, List<DestinationRequestDTO> data);
    DestinationResponseDTO updateDestination(UUID destinationId, DestinationRequestDTO data);

    void deleteDestination(UUID destinationId);
    void deleteDestinations(List<UUID> destinationIds);

}
