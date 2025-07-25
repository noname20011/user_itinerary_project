package domain.travel.travel_itinerary.dto.user_badge_progress;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.domain.enums.StatusProgressEnum;
import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
public class UserBadgeProgressResponseDTO implements Serializable {

    private User user;

    private BadgeRequestDTO badge;

    private StatusProgressEnum status;
    
    private Integer progressValue;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
