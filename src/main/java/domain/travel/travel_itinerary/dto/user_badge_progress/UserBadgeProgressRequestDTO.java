package domain.travel.travel_itinerary.dto.user_badge_progress;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.domain.enums.StatusProgressEnum;
import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Builder
@Setter
public class UserBadgeProgressRequestDTO implements Serializable {

    @NotNull
    private User user;

    @NotNull
    private BadgeRequestDTO badge;

    @NotNull
    private StatusProgressEnum status;

    private Integer progressValue;

}
