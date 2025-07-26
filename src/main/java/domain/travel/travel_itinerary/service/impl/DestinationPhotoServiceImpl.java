package domain.travel.travel_itinerary.service.impl;

import domain.travel.travel_itinerary.domain.entity.DestinationPhotos;
import domain.travel.travel_itinerary.dto.destination_photo.DestinationPhotoRequestDTO;
import domain.travel.travel_itinerary.dto.destination_photo.DestinationPhotoResponseDTO;
import domain.travel.travel_itinerary.exception.UploadFileFailException;
import domain.travel.travel_itinerary.mapper.DestinationPhotosMapper;
import domain.travel.travel_itinerary.repository.DestinationPhotoRepository;
import domain.travel.travel_itinerary.service.CloudinaryService;
import domain.travel.travel_itinerary.service.DestinationPhotoService;
import domain.travel.travel_itinerary.service.helper.AsyncService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class DestinationPhotoServiceImpl implements DestinationPhotoService {

    private final DestinationPhotoRepository destinationPhotoRepository;
    private final AsyncService asyncService;
    private final DestinationPhotosMapper destinationPhotosMapper;

    @Override
    public Page<DestinationPhotoResponseDTO> getDestinationPhotos(UUID destinationId, Pageable pageable) {
        Page<DestinationPhotos> result = destinationPhotoRepository.findByDestinationId(destinationId, pageable);
        return result.map(destinationPhotosMapper::mapToResponseDto);
    }

        @Transactional
        @Override
        public List<DestinationPhotos> addDestinationPhotos(UUID destinationId, List<DestinationPhotoRequestDTO> requestDTOs) {
            try {
                if (requestDTOs == null || requestDTOs.isEmpty()) {
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
                List<DestinationPhotos> data = destinationPhotosMapper.mapToListEntities(requestDTOs);
                destinationPhotoRepository.saveAll(data);

    //            Response photos by destinationId
                return destinationPhotoRepository.getAllByDestination_Id(destinationId);
            } catch (UploadFileFailException e) {
                throw new UploadFileFailException("Upload photo fail");
            }
        }

    @Transactional
    @Override
    public List<DestinationPhotos> updateDestinationPhotos(UUID destinationId, List<DestinationPhotoRequestDTO> requestDTOs) {
        try {
//            When user delete all photo
            if (requestDTOs == null || requestDTOs.isEmpty()) {
                deleteAllDestinationPhotos(destinationId);
                return new ArrayList<>();
            }

//          Get all photo by destination
            List<DestinationPhotos> currentPhotos = destinationPhotoRepository.getAllByDestination_Id(destinationId);

//            Get data saved photo from client
            List<String> savedPhotos = requestDTOs.stream()
                    .map(DestinationPhotoRequestDTO::getPhotoUrl)
                    .filter(Objects::nonNull).toList();

//            Get delete photo after user update
            List<DestinationPhotos> deletePhotos = currentPhotos.stream()
                    .filter(photo ->
                            !savedPhotos.contains(photo.getPhotoUrl())
                    ).toList();

//            Delete photo from DB after update
            destinationPhotoRepository.deleteAll(deletePhotos);

//            Delete photos from Cloudinary
            List<String> urls = deletePhotos.stream().map(DestinationPhotos::getPhotoUrl).toList();
            urls.forEach(asyncService::deletePhoto);

//            Get new photo by check request has multiple file
            List<DestinationPhotoRequestDTO> newPhotos = requestDTOs.stream()
                    .filter(image ->
                            image.getFilePhoto() != null && !image.getFilePhoto().getFilePhoto().isEmpty()).toList();

            return addDestinationPhotos(destinationId, newPhotos);
        } catch (RuntimeException e) {
            throw new UploadFileFailException(e.getMessage());
        }
    }


    @Override
    public void deleteDestinationPhoto(List<DestinationPhotos> photos) {
        destinationPhotoRepository.deleteAll(photos);
    }


//    Separate execute delete all photo to clean code
    @Transactional
    @Override
    public void deleteAllDestinationPhotos (UUID destinationId) {
        //                Get list photo to delete from cloudinary
        List<DestinationPhotos> dataToDeletes = destinationPhotoRepository.getAllByDestination_Id(destinationId);
        List<String> urls = dataToDeletes.stream().map(DestinationPhotos::getPhotoUrl).toList();
        urls.forEach(asyncService::deletePhoto);

//                Delete all photos by destinationId
        destinationPhotoRepository.deleteDestinationPhotosByDestination_Id(destinationId);
    }
}
