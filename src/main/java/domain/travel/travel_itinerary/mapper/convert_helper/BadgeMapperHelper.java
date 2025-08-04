package domain.travel.travel_itinerary.mapper.convert_helper;

import domain.travel.travel_itinerary.domain.entity.TypeBadge;
import domain.travel.travel_itinerary.repository.TypeBadgeRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BadgeMapperHelper {
    private final TypeBadgeRepository typeBadgeRepository;

    @Named("getTypeBadgeById")
    public TypeBadge getTypeBadgeById(UUID id) {
        return typeBadgeRepository.findByIdOrThrow(id);
    }
}
