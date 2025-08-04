package domain.travel.travel_itinerary.mapper;

import domain.travel.travel_itinerary.domain.entity.Challenge;
import domain.travel.travel_itinerary.dto.challenge.ChallengeRequestDTO;
import domain.travel.travel_itinerary.dto.challenge.ChallengeResponseDTO;
import domain.travel.travel_itinerary.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChallengeMapper extends BaseMapper<Challenge, ChallengeRequestDTO, ChallengeResponseDTO> {
}
