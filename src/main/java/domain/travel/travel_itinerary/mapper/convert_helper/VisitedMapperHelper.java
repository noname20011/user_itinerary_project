package domain.travel.travel_itinerary.mapper.convert_helper;

import domain.travel.travel_itinerary.domain.entity.Destination;
import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.repository.DestinationRepository;
import domain.travel.travel_itinerary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VisitedMapperHelper {
    private final UserRepository repository;
    private final DestinationRepository destinationRepository;

    @Named("getByUserId")
    public User getByUserId(UUID userId) {
        return repository.findByIdOrThrow(userId);
    }

    @Named("getByDestinationId")
    public Destination getDestinationId(UUID destinationId) {
        return destinationRepository.findByIdOrThrow(destinationId);
    }
}
