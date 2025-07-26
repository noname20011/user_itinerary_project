package domain.travel.travel_itinerary.mapper.convert_helper;

import domain.travel.travel_itinerary.domain.entity.Visited;
import domain.travel.travel_itinerary.repository.VisitedRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VisitedPhotoMapperHelper {
    private final VisitedRepository repository;

    @Named("getByVisitedId")
    public Visited getByVisitedId(UUID visitedId) {
        return repository.findByIdOrThrow(visitedId);
    }
}
