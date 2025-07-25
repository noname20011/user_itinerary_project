package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.user.UserRequestDTO;
import domain.travel.travel_itinerary.dto.user.UserResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserRequestDTO, UserResponseDTO> {
}
