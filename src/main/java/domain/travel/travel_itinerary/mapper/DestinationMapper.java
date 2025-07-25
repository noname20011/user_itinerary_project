package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.Destination;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import domain.travel.travel_itinerary.dto.destination.DestinationResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import domain.travel.travel_itinerary.mapper.helper.DestinationMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = DestinationMapperHelper.class)
public interface DestinationMapper extends BaseMapper<Destination, DestinationRequestDTO, DestinationResponseDTO> {

    @Mapping(source = "provinceId", target = "province", qualifiedByName = "getProvinceById")
    @Override
    Destination mapToEntity(DestinationRequestDTO destinationRequestDTO);

    @Mapping(source = "province.id", target = "provinceId")
    @Override
    DestinationResponseDTO mapToResponseDto(Destination destination);

}
