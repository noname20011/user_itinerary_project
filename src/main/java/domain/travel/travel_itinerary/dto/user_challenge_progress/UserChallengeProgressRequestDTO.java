package domain.travel.travel_itinerary.dto.user_challenge_progress;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.challenge.ChallengeRequestDTO;
import domain.travel.travel_itinerary.domain.enums.StatusProgressEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@Setter
public class UserChallengeProgressRequestDTO implements Serializable {

    @NotNull
    private ChallengeRequestDTO challenge;

    @NotNull
    private User user;

    @NotNull
    private StatusProgressEnum status;

    private Integer progressValue;
}
