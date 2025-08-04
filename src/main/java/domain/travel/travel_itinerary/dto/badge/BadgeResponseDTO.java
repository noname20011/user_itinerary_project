package domain.travel.travel_itinerary.dto.badge;

import com.fasterxml.jackson.annotation.JsonInclude;
import domain.travel.travel_itinerary.domain.enums.BadgeStatusEnum;
import domain.travel.travel_itinerary.dto.challenge.ChallengeResponseDTO;
import domain.travel.travel_itinerary.dto.user_challenge_progress.UserChallengeProgressResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class BadgeResponseDTO implements Serializable {

    private UUID id;
    private String name;
    private String thumbnail;
    private BadgeStatusEnum status;
    private UUID typeBadgeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long totalChallenges;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long totalChallengesUserDoing;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String conditionJson;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ChallengeResponseDTO> challenges;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
