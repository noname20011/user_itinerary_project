package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.TypeBadge;
import domain.travel.travel_itinerary.dto.type_badge.TypeBadgeRequestDTO;
import domain.travel.travel_itinerary.dto.type_badge.TypeBadgeResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TypeBadgeMapper extends BaseMapper<TypeBadge, TypeBadgeRequestDTO, TypeBadgeResponseDTO> {


    @Override
    TypeBadgeResponseDTO mapToResponseDto(TypeBadge typeBadge);
}
