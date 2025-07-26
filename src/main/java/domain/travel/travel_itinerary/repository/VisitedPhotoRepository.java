package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.VisitedPhoto;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VisitedPhotoRepository extends BaseRepository<VisitedPhoto, UUID> {
    List<VisitedPhoto> findAllByVisited_Id(UUID visitedId);
    void deleteAllByVisited_Id(UUID visitedId);
}
