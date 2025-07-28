package domain.travel.travel_itinerary.mapper.convert_helper;

import domain.travel.travel_itinerary.domain.entity.Destination;
import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.whitelist.WhiteListResponseDTO;
import domain.travel.travel_itinerary.repository.DestinationRepository;
import domain.travel.travel_itinerary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WhiteListMapperHelper {

    private final DestinationRepository destinationRepository;
    private final UserRepository userRepository;

    @Named("getByDestinationId")
    public Destination getByDestinationId(UUID destinationId) {
        return destinationRepository.findByIdOrThrow(destinationId);
    }

    @Named("getByUserId")
    public User getByUserId(UUID userId) {
        return userRepository.findByIdOrThrow(userId);
    }
}