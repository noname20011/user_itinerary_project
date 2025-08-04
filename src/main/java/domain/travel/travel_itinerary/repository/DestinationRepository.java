package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.Destination;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DestinationRepository extends BaseRepository<Destination, UUID> {
    Page<Destination> getAllByProvince_Id(UUID provinceId, Pageable pageable);
}
