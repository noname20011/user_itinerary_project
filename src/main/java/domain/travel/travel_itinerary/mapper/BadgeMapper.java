package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.Badge;
import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import domain.travel.travel_itinerary.dto.badge.BadgeResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import domain.travel.travel_itinerary.mapper.convert_helper.BadgeMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = BadgeMapperHelper.class)
public interface BadgeMapper extends BaseMapper<Badge, BadgeRequestDTO, BadgeResponseDTO> {

    @Mapping(source = "typeBadgeId", target = "typeBadge", qualifiedByName = "getTypeBadgeById")
    @Override
    Badge mapToEntity(BadgeRequestDTO badgeRequestDTO);


    @Mapping(source = "typeBadge.id", target = "typeBadgeId")
    @Override
    BadgeResponseDTO mapToResponseDto(Badge badge);
}
