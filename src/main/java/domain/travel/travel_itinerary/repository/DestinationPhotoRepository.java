package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.DestinationPhotos;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DestinationPhotoRepository extends BaseRepository<DestinationPhotos, UUID> {
    Page<DestinationPhotos> findByDestinationId(UUID destinationId, Pageable pageable);
    List<DestinationPhotos> getAllByDestination_Id(UUID destinationId);
    void deleteDestinationPhotosByDestination_Id(UUID destinationId);
}
