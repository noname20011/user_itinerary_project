package domain.travel.travel_itinerary.dto.destination;

import domain.travel.travel_itinerary.domain.entity.DestinationPhotos;
import domain.travel.travel_itinerary.domain.enums.DestinationStatusEnum;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class DestinationResponseDTO implements Serializable {

    private UUID id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private DestinationStatusEnum status;
    private String statusMessage;
    private UUID provinceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<DestinationPhotos> photos;
}
