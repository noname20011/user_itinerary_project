package domain.travel.travel_itinerary.service.impl;

import domain.travel.travel_itinerary.domain.entity.Destination;
import domain.travel.travel_itinerary.domain.entity.DestinationPhotos;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import domain.travel.travel_itinerary.dto.destination.DestinationResponseDTO;
import domain.travel.travel_itinerary.dto.destination_photo.DestinationPhotoRequestDTO;
import domain.travel.travel_itinerary.exception.NotFoundException;
import domain.travel.travel_itinerary.mapper.DestinationMapper;
import domain.travel.travel_itinerary.repository.DestinationRepository;
import domain.travel.travel_itinerary.repository.custom_repository.province.PagingResult;
import domain.travel.travel_itinerary.service.DestinationPhotoService;
import domain.travel.travel_itinerary.service.DestinationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationPhotoService destinationPhotoService;
    private final DestinationMapper destinationMapper;

    @Override
    public PagingResult<DestinationResponseDTO> getAllDestinationByProvince(UUID provinceId, Pageable pageable) {
        Pageable formatPageSize = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        Page<Destination> destinations = destinationRepository.getAllByProvince_Id(provinceId, formatPageSize);
        List<DestinationResponseDTO> responseDTOS =
                destinations.map(destinationMapper::mapToResponseDto).toList();
        return new PagingResult<>(pageable.getPageNumber(), pageable.getPageSize(), destinations.getTotalElements(), responseDTOS);
    }

    @Override
    public DestinationResponseDTO getDestination(UUID destinationId) {
        Destination destination = getEntityDestination(destinationId);
        return destinationMapper.mapToResponseDto(destination);
    }


    @Override
    public List<DestinationResponseDTO> addDestinations(List<DestinationRequestDTO> destinationRequestDTOS) {
        if (destinationRequestDTOS.isEmpty()) return new ArrayList<>();
        return destinationRequestDTOS.stream().map(this::addDestination).toList();
    }

    @Transactional
    @Override
    public DestinationResponseDTO addDestination(DestinationRequestDTO destinationRequestDTO) {
        Destination  destination = destinationMapper.mapToEntity(destinationRequestDTO);
        Destination result = destinationRepository.save(destination);

//        Check if add Photo to execute add Photo
        if(destinationRequestDTO.getFilePhotos() != null) {
            List<DestinationPhotoRequestDTO> photoRequestDTOS =
                    destinationRequestDTO.getFilePhotos().stream()
                            .map(filePhoto -> new DestinationPhotoRequestDTO(result.getId(), filePhoto)).toList();
            List<DestinationPhotos> responsePhotos = destinationPhotoService.addDestinationPhotos(result.getId(), photoRequestDTOS);
//            Reset response Photo to destination
            result.setPhotos(responsePhotos);
        }

        return destinationMapper.mapToResponseDto(result);
    }

    @Override
    public List<DestinationResponseDTO> updateDestinations(UUID destinationId, List<DestinationRequestDTO> listDto) {
        return listDto.stream().map(this::addDestination).collect(Collectors.toList());
    }

    @Override
    public DestinationResponseDTO updateDestination(UUID destinationId, DestinationRequestDTO dto) {
        Destination  destination = destinationMapper.mapToEntity(dto);
        Destination result = getEntityDestination(destinationId);

        List<DestinationPhotoRequestDTO> photoRequestDTOS = new ArrayList<>();

        //      Check if add new files photo then add to list
        if(!dto.getFilePhotos().isEmpty()) {
            photoRequestDTOS.addAll(dto.getFilePhotos().stream()
                            .map(filePhoto ->
                                    new DestinationPhotoRequestDTO(result.getId(), filePhoto )).toList()
            );
        }
//      Check if saving old photos then add to list
        if(!dto.getPhotosUrl().isEmpty()){
            photoRequestDTOS.addAll(dto.getPhotosUrl().stream()
                                    .map(filePhoto ->
                                            new DestinationPhotoRequestDTO(result.getId(), filePhoto )).toList()
            );
        }

        List<DestinationPhotos> responsePhotos =
                destinationPhotoService.updateDestinationPhotos(result.getId(), photoRequestDTOS);

        //            Reset response Photo to destination
        result.setPhotos(responsePhotos);
        return destinationMapper.mapToResponseDto(result);
    }

    @Transactional
    @Override
    public void deleteDestination(UUID destinationId) {
        Destination result = getEntityDestination(destinationId);
        destinationPhotoService.deleteAllDestinationPhotos(destinationId);
        destinationRepository.delete(result);
    }

    @Override
    public void deleteDestinations(List<UUID> destinationIds) {
        if (!destinationIds.isEmpty()) {
            destinationIds.forEach(this::deleteDestination);
        }
    }

//  Sub function to get entity Destination
    private Destination getEntityDestination(UUID destinationId) {
        return destinationRepository.findById(destinationId)
                .orElseThrow(() -> new NotFoundException("Destination not found with id " + destinationId));
    }

}
