package domain.travel.travel_itinerary.dto.visited;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class VisitedResponseDTO implements Serializable {

    private UUID destinationId;

    private UUID userId;

    private LocalDate visitedTime;

    private String reviewText;

    private Integer rating;

    private String photoUrl;

}
