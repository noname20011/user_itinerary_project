package domain.travel.travel_itinerary.mapper.helper;


import domain.travel.travel_itinerary.domain.entity.Destination;
import domain.travel.travel_itinerary.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DestinationPhotoMapperHelper {
    private final DestinationRepository destinationRepository;

    @Named("getByDestinationId")
    public Destination getByDestinationId(UUID destinationId) {
        return destinationId == null ? null : destinationRepository.findByIdOrThrow(destinationId);
    }
}
