package domain.travel.travel_itinerary.dto.user_challenge_progress;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.domain.enums.StatusProgressEnum;
import domain.travel.travel_itinerary.dto.challenge.ChallengeRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
public class UserChallengeProgressResponseDTO implements Serializable {

    private ChallengeRequestDTO challenge;

    private User user;

    private StatusProgressEnum status;

    private Integer progressValue;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
