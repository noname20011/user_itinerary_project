package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.TypeBadge;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypeBadgeRepository extends BaseRepository<TypeBadge, UUID> {
}
