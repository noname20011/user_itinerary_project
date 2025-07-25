package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.domain.entity.DestinationPhotos;
import domain.travel.travel_itinerary.dto.destination_photo.DestinationPhotoRequestDTO;
import domain.travel.travel_itinerary.dto.destination_photo.DestinationPhotoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DestinationPhotoService {
    Page<DestinationPhotoResponseDTO> getDestinationPhotos(UUID destinationId, Pageable pageable);

    List<DestinationPhotos> addDestinationPhotos(UUID destinationId, List<DestinationPhotoRequestDTO> requestDTOs);

    List<DestinationPhotos> updateDestinationPhotos(UUID destinationId, List<DestinationPhotoRequestDTO> requestDTOs);

    void deleteDestinationPhoto(List<DestinationPhotos> photoIds);

    void deleteAllDestinationPhotos(UUID destinationId);

}
