package domain.travel.travel_itinerary.dto.challenge;

import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ChallengeResponseDTO implements Serializable {

    private UUID id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BadgeRequestDTO badge;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
