package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.Badge;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import domain.travel.travel_itinerary.repository.custom_repository.badge.BadgeRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BadgeRepository extends BaseRepository<Badge, UUID>, BadgeRepositoryCustom {
    Page<Badge> findAllByTypeBadgeId(UUID typeBadgeId, Pageable pageable);

}
