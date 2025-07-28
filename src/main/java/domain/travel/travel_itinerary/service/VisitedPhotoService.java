package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.domain.entity.VisitedPhoto;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoRequestDTO;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface VisitedPhotoService {
    List<VisitedPhotoResponseDTO> getAllPhotosByVisited(UUID visitedId);

    List<VisitedPhoto> addVisitedPhotos(UUID visitedId, List<VisitedPhotoRequestDTO> requestDTOs);

    List<VisitedPhoto> updateVisitedPhotos(UUID visitedId, List<VisitedPhotoRequestDTO> requestDTOs);

    void deleteVisitedPhoto(List<VisitedPhoto> requestDTOs);

    void deleteAllVisitedPhotos(UUID visitedId);

}
