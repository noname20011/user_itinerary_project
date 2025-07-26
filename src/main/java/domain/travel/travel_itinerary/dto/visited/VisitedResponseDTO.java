package domain.travel.travel_itinerary.dto.visited;

import domain.travel.travel_itinerary.domain.entity.VisitedPhoto;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class VisitedResponseDTO implements Serializable {

    private UUID id;
    private UUID destinationId;
    private UUID userId;
    private LocalDate visitedTime;
    private String reviewText;
    private String tag;
    private List<VisitedPhoto> photos;

}
