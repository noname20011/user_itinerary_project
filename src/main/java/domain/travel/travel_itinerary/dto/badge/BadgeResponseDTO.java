package domain.travel.travel_itinerary.dto.badge;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class BadgeResponseDTO implements Serializable {

    private UUID id;
    private String name;
    private String description;
    private String thumbnail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
