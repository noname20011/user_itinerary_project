package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.DestinationPhotos;
import domain.travel.travel_itinerary.dto.destination_photo.DestinationPhotoRequestDTO;
import domain.travel.travel_itinerary.dto.destination_photo.DestinationPhotoResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import domain.travel.travel_itinerary.mapper.convert_helper.DestinationPhotoMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = DestinationPhotoMapperHelper.class)
public interface DestinationPhotosMapper extends BaseMapper<DestinationPhotos, DestinationPhotoRequestDTO, DestinationPhotoResponseDTO> {
    ;

    @Mapping(source = "destinationId", target = "destination", qualifiedByName = "getByDestinationId")
    @Override
    DestinationPhotos mapToEntity(DestinationPhotoRequestDTO destinationPhotoRequestDTO);

    @Mapping(source = "destination.id", target = "destinationId")
    @Override
    DestinationPhotoResponseDTO mapToResponseDto(DestinationPhotos destinationPhotos);

}
