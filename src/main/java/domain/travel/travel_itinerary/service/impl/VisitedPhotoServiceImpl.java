package domain.travel.travel_itinerary.service.impl;

import domain.travel.travel_itinerary.domain.entity.VisitedPhoto;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoRequestDTO;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoResponseDTO;
import domain.travel.travel_itinerary.exception.UploadFileFailException;
import domain.travel.travel_itinerary.mapper.VisitedPhotoMapper;
import domain.travel.travel_itinerary.repository.VisitedPhotoRepository;
import domain.travel.travel_itinerary.service.VisitedPhotoService;
import domain.travel.travel_itinerary.service.helper.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class VisitedPhotoServiceImpl implements VisitedPhotoService {

    private final VisitedPhotoRepository visitedPhotoRepository;
    private final VisitedPhotoMapper visitedPhotoMapper;
    private final AsyncService asyncService;

    @Override
    public List<VisitedPhotoResponseDTO> getAllPhotosByVisited(UUID visitedId) {
        List<VisitedPhoto> result = visitedPhotoRepository.findAllByVisited_Id(visitedId);
        return result.stream().map(visitedPhotoMapper::mapToResponseDto).toList();
    }

    @Override
    public List<VisitedPhoto> addVisitedPhotos(UUID visitedId, List<VisitedPhotoRequestDTO> requestDTOs) {
        try {
            if (requestDTOs == null || requestDTOs.isEmpty()) {;
                return new ArrayList<>();
            }

//                Async upload image to Cloudinary
            List<CompletableFuture<String>> futures = requestDTOs.stream()
                    .map(requestDTO -> asyncService.uploadPhoto(requestDTO.getFilePhoto()))
                    .toList();

//                await to get all urls
            List<String> urls = futures.stream().map(CompletableFuture::join).toList();

            for (int i = 0; i < requestDTOs.size(); i++) {
                requestDTOs.get(i).setPhotoUrl(urls.get(i));
            }

            //            Mapper to entity to save
            List<VisitedPhoto> data = visitedPhotoMapper.mapToListEntities(requestDTOs);
            visitedPhotoRepository.saveAll(data);

            //            Response photos by destinationId
            return visitedPhotoRepository.findAllByVisited_Id(visitedId);
        } catch (UploadFileFailException e) {
            throw new UploadFileFailException("Upload photo fail");
        }
    }

    @Override
    public List<VisitedPhoto> updateVisitedPhotos(UUID visitedId, List<VisitedPhotoRequestDTO> requestDTOs) {
        try {
//            When user delete all photo
            if (requestDTOs == null || requestDTOs.isEmpty()) {
                deleteAllVisitedPhotos(visitedId);
                return new ArrayList<>();
            }

//          Get all photo by destination
            List<VisitedPhoto> currentPhotos = visitedPhotoRepository.findAllByVisited_Id(visitedId);

//            Get delete photo after user update
            List<VisitedPhoto> deletePhotos = currentPhotos.stream()
                    .filter(photo ->
                            requestDTOs.stream()
                                    .noneMatch(dto ->
                                            Objects.equals(dto.getPhotoUrl(),photo.getPhotoUrl()))
                    ).toList();

//            Delete photo from DB after update
            visitedPhotoRepository.deleteAll(deletePhotos);

//            Delete photos from Cloudinary
            List<String> urls = deletePhotos.stream().map(VisitedPhoto::getPhotoUrl).toList();
            urls.forEach(asyncService::deletePhoto);

//            Get new photo by check request has multiple file
            List<VisitedPhotoRequestDTO> newPhotos = requestDTOs.stream()
                    .filter(image ->
                            image.getFilePhoto() != null && !image.getFilePhoto().getFilePhoto().isEmpty()).toList();

            return addVisitedPhotos(visitedId, newPhotos);
        } catch (RuntimeException e) {
            throw new UploadFileFailException(e.getMessage());
        }
    }

    @Override
    public void deleteVisitedPhoto(List<VisitedPhoto>  requestDTOs) {
        visitedPhotoRepository.deleteAll(requestDTOs);
    }

    @Override
    public void deleteAllVisitedPhotos(UUID visitedId) {
        //                Get list photo to delete from cloudinary
        List<VisitedPhoto> dataToDeletes = visitedPhotoRepository.findAllByVisited_Id(visitedId);
        List<String> urls = dataToDeletes.stream().map(VisitedPhoto::getPhotoUrl).toList();
        urls.forEach(asyncService::deletePhoto);

//                Delete all photos by visitedId
        visitedPhotoRepository.deleteAllByVisited_Id(visitedId);
    }
}
