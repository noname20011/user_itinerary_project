package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.Visited;
import domain.travel.travel_itinerary.dto.visited.VisitedRequestDTO;
import domain.travel.travel_itinerary.dto.visited.VisitedResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import domain.travel.travel_itinerary.mapper.convert_helper.VisitedMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
uses = {VisitedMapperHelper.class})
public interface VisitedMapper extends BaseMapper<Visited, VisitedRequestDTO, VisitedResponseDTO> {

    @Mapping(source = "userId", target = "user", qualifiedByName = "getByUserId")
    @Mapping(source = "destinationId", target = "destination", qualifiedByName = "getByDestinationId")
    @Override
    Visited mapToEntity(VisitedRequestDTO visitedRequestDTO);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "destination.id", target = "destinationId")
    @Override
    VisitedResponseDTO mapToResponseDto(Visited visited);
}
