package domain.travel.travel_itinerary.dto.challenge;

import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@Setter
public class ChallengeRequestDTO implements Serializable {

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BadgeRequestDTO badge;

}
