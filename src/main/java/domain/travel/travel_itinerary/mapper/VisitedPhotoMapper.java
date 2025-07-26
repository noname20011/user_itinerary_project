package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.VisitedPhoto;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoRequestDTO;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import domain.travel.travel_itinerary.mapper.convert_helper.VisitedPhotoMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses =  {VisitedPhotoMapperHelper.class})
public interface VisitedPhotoMapper extends BaseMapper<VisitedPhoto, VisitedPhotoRequestDTO, VisitedPhotoResponseDTO> {

    @Mapping(source = "visitedId", target = "visited", qualifiedByName = "getByVisitedId")
    @Override
    VisitedPhoto mapToEntity(VisitedPhotoRequestDTO visitedPhotoRequestDTO);

    @Mapping(source = "visited.id", target = "visitedId")
    @Override
    VisitedPhotoResponseDTO mapToResponseDto(VisitedPhoto visitedPhoto);
}
