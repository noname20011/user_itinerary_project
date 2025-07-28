package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.WhiteList;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface WhiteListRepository extends BaseRepository<WhiteList, UUID> {
    List<WhiteList> getAllByUserIdOrderByCreatedAtDesc(UUID userId);

    @Query(value = "select w.destination.id from WhiteList w where w.user = :userId")
    Set<UUID> getAllDestinationIdByUserId(@Param("userId") UUID userId);

    boolean existsByUserIdAndDestinationId(UUID userId, UUID destinationId);

    void deleteByDestinationId(UUID destinationId);
    void deleteByDestinationIdAndUserId(UUID destinationId, UUID userId);
}
