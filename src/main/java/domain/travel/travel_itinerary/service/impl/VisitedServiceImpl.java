package domain.travel.travel_itinerary.service.impl;

import domain.travel.travel_itinerary.domain.entity.Visited;
import domain.travel.travel_itinerary.domain.entity.VisitedPhoto;
import domain.travel.travel_itinerary.dto.visited.VisitedRequestDTO;
import domain.travel.travel_itinerary.dto.visited.VisitedResponseDTO;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoRequestDTO;
import domain.travel.travel_itinerary.exception.NotFoundException;
import domain.travel.travel_itinerary.helper.dto.FilePhotoDTO;
import domain.travel.travel_itinerary.mapper.VisitedMapper;
import domain.travel.travel_itinerary.repository.VisitedRepository;
import domain.travel.travel_itinerary.repository.custom_repository.province.PagingResult;
import domain.travel.travel_itinerary.service.VisitedPhotoService;
import domain.travel.travel_itinerary.service.VisitedService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitedServiceImpl implements VisitedService {

    private final VisitedRepository visitedRepository;
    private final VisitedPhotoService visitedPhotoService;
    private final VisitedMapper visitedMapper;


    @Transactional
    @Override
    public PagingResult<VisitedResponseDTO> getAllVisitedByUserId(UUID userId, Pageable pageable) {
        Pageable pageSizeFormat = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        Page<Visited> destinations = visitedRepository.findAllByUser_Id(userId, pageSizeFormat);
        List<VisitedResponseDTO> responseDTOS =
                destinations.map(visitedMapper::mapToResponseDto).toList();
        return new PagingResult<>(pageable.getPageNumber(), pageable.getPageSize(), destinations.getTotalElements(), responseDTOS);
    }

    @Transactional
    @Override
    public VisitedResponseDTO getVisited(UUID visitedId) {
        Visited visited = getEntityVisited(visitedId);
        return visitedMapper.mapToResponseDto(visited);
    }

    @Transactional
    @Override
    public VisitedResponseDTO addVisited(VisitedRequestDTO requestDTO) {
        Visited visited = visitedMapper.mapToEntity(requestDTO);
        Visited result = visitedRepository.save(visited);

//        Check if add Photo to execute add Photo
        if (requestDTO.getPhotoFiles() != null) {
            List<VisitedPhotoRequestDTO> photoRequestDTOS =
                    requestDTO.getPhotoFiles().stream()
                            .map(filePhoto -> new VisitedPhotoRequestDTO(result.getId(), new FilePhotoDTO(filePhoto))).toList();
            List<VisitedPhoto> responsePhotos = visitedPhotoService.addVisitedPhotos(result.getId(), photoRequestDTOS);
//            Reset response Photo to visited
            result.setPhotos(responsePhotos);
        }

        return visitedMapper.mapToResponseDto(result);
    }


    @Transactional
    @Override
    public VisitedResponseDTO updateVisited(UUID visitedId, VisitedRequestDTO dto) {

        Visited result = getEntityVisited(visitedId);

        List<VisitedPhotoRequestDTO> photoRequestDTOS = new ArrayList<>();

        //      Check if add new files photo then add to list
        if (dto.getPhotoFiles()!= null && !dto.getPhotoFiles().isEmpty()) {
            photoRequestDTOS.addAll(dto.getPhotoFiles().stream()
                    .map(filePhoto ->
                            new VisitedPhotoRequestDTO(visitedId, new FilePhotoDTO(filePhoto))).toList()
            );
        }
//      Check if saving old photos then add to list
        if (dto.getPhotosUrl() != null && !dto.getPhotosUrl().isEmpty()) {
            photoRequestDTOS.addAll(dto.getPhotosUrl().stream()
                    .map(filePhoto ->
                            new VisitedPhotoRequestDTO(visitedId, filePhoto)).toList()
            );
        }

//        Update data visited
        Visited toEntity = visitedMapper.mapToEntity(dto);

        Visited response = visitedRepository.update(visitedId, toEntity);
//            Update photo response Photo of visited
        List<VisitedPhoto> responsePhotos =
                visitedPhotoService.updateVisitedPhotos(result.getId(), photoRequestDTOS);

//            Reset response Photo of visited
        response.setPhotos(responsePhotos);
        return visitedMapper.mapToResponseDto(result);
    }

    @Transactional
    @Override
    public void deleteVisited(UUID visitedId) {
        Visited result = getEntityVisited(visitedId);
        visitedPhotoService.deleteAllVisitedPhotos(visitedId);
        visitedRepository.delete(result);
    }

    @Transactional
    @Override
    public void deleteListVisited(List<UUID> visitedIds) {
        if (!visitedIds.isEmpty()) {
            visitedIds.forEach(this::deleteVisited);
        }
    }

    //  Sub function to get entity Destination
    private Visited getEntityVisited(UUID visitedId) {
        return visitedRepository.findById(visitedId)
                .orElseThrow(() -> new NotFoundException("Visited not found with id " + visitedId));
    }
}
