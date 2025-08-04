package domain.travel.travel_itinerary.dto.challenge;

import com.fasterxml.jackson.annotation.JsonInclude;
import domain.travel.travel_itinerary.domain.enums.StatusProgressEnum;
import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import domain.travel.travel_itinerary.dto.badge.BadgeResponseDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ChallengeResponseDTO implements Serializable {

    private UUID id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusProgressEnum status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double progress;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String conditionJson;
}
