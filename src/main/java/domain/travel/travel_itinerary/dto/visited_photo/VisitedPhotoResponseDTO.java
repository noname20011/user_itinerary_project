package domain.travel.travel_itinerary.dto.visited_photo;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
public class VisitedPhotoResponseDTO implements Serializable {
    private UUID id;
    private UUID visitedId;
    private String reviewText;
    private String tag;
    private String photoUrl;
}
