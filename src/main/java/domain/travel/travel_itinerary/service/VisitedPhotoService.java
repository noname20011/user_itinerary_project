package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.domain.entity.DestinationPhotos;
import domain.travel.travel_itinerary.domain.entity.VisitedPhoto;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import domain.travel.travel_itinerary.dto.destination.DestinationResponseDTO;
import domain.travel.travel_itinerary.dto.destination_photo.DestinationPhotoRequestDTO;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoRequestDTO;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoResponseDTO;
import domain.travel.travel_itinerary.repository.custom_repository.province.PagingResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface VisitedPhotoService {
    List<VisitedPhotoResponseDTO> getAllPhotosByVisited(UUID visitedId);

    List<VisitedPhoto> addVisitedPhotos(UUID visitedId, List<VisitedPhotoRequestDTO> requestDTOs);

    List<VisitedPhoto> updateVisitedPhotos(UUID visitedId, List<VisitedPhotoRequestDTO> requestDTOs);

    void deleteVisitedPhoto(List<VisitedPhoto> requestDTOs);

    void deleteAllVisitedPhotos(UUID visitedId);
}
