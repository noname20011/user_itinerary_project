package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.Province;
import domain.travel.travel_itinerary.dto.province.ProvinceRequestDTO;
import domain.travel.travel_itinerary.dto.province.ProvinceResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProvinceMapper extends BaseMapper<Province, ProvinceRequestDTO, ProvinceResponseDTO> {

}
