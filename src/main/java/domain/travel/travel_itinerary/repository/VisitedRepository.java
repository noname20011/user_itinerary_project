package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.Visited;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VisitedRepository extends BaseRepository<Visited, UUID> {
    Page<Visited> findAllByUser_Id(UUID userId, Pageable pageable);
}
