package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.WhiteList;
import domain.travel.travel_itinerary.dto.whitelist.WhiteListRequestDTO;
import domain.travel.travel_itinerary.dto.whitelist.WhiteListResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import domain.travel.travel_itinerary.mapper.convert_helper.WhiteListMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = WhiteListMapperHelper.class)
public interface WhiteListMapper extends BaseMapper<WhiteList, WhiteListRequestDTO, WhiteListResponseDTO> {

    @Mapping(source = "userId", target = "user", qualifiedByName = "getByUserId")
    @Mapping(source = "destinationId", target = "destination", qualifiedByName = "getByDestinationId")
    @Override
    WhiteList mapToEntity(WhiteListRequestDTO whiteListRequestDTO);

    @Mapping(source = "user.id", target = "userId")
    @Override
    WhiteListResponseDTO mapToResponseDto(WhiteList whiteList);
}
