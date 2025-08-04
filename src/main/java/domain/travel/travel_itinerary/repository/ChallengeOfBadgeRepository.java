package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.Challenge;
import domain.travel.travel_itinerary.domain.entity.ChallengeOfBadge;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ChallengeOfBadgeRepository extends BaseRepository<ChallengeOfBadge, UUID> {

    @Query(value = "select cb.challenge from ChallengeOfBadge cb where cb.badge.id = :badgeId")
    List<UUID> findAllChallengeIdsByBadgeId(@Param("badgeId") UUID badgeId);

    @Transactional
    @Modifying
    @Query(value = "delete from ChallengeOfBadge cb where cb.badge.id =:badgeId and cb.challenge.id IN :challengeIds")
    void deleteByBadgeIdAndChallengeIdIn(@Param("badgeId") UUID badgeId, @Param("challengeIds") Set<UUID> challengeIds);

}
