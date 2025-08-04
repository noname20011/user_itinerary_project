package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import domain.travel.travel_itinerary.dto.badge.BadgeResponseDTO;
import domain.travel.travel_itinerary.repository.custom_repository.PagingResult;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BadgeService {

    PagingResult<BadgeResponseDTO> getAllBadges(Pageable pageable);
    PagingResult<BadgeResponseDTO> getAllBadgesByUser(UUID userId, Pageable pageable);
    PagingResult<BadgeResponseDTO> getAllBadgesByTypeBadge(UUID typeBadgeId, Pageable pageable);
    PagingResult<BadgeResponseDTO> getAllBadgesByUserAndTypeBadge(UUID userId, UUID typeBadgeId, Pageable pageable);
    BadgeResponseDTO getDetailBadgeForUser(UUID userId, UUID badgeId);

    BadgeResponseDTO getBadge(UUID badgeId);
    BadgeResponseDTO addBadge(BadgeRequestDTO requestDTO);
    BadgeResponseDTO updateBadge(UUID badgeId, BadgeRequestDTO requestDTO);
    void deleteBadge(UUID badgeId);

}
