package domain.travel.travel_itinerary.repository.custom_repository.badge;

import domain.travel.travel_itinerary.dto.badge.BadgeResponseDTO;
import domain.travel.travel_itinerary.repository.custom_repository.PagingResult;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BadgeRepositoryCustom {
    PagingResult<BadgeResponseDTO> getBadgesHasUserData(UUID userId, Pageable pageable);
    PagingResult<BadgeResponseDTO> getBadgesHasUserByTypeBadgeId(UUID userId, UUID typeBadgeId, Pageable pageable);


    BadgeResponseDTO getBadgeById(UUID badgeId);
    BadgeResponseDTO getBadgeByIdForUser(UUID badgeId, UUID userId);

}
