package domain.travel.travel_itinerary.dto.whitelist;

import domain.travel.travel_itinerary.domain.entity.Destination;
import domain.travel.travel_itinerary.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class WhiteListResponseDTO implements Serializable {

    private UUID id;

    private Destination destination;

    private UUID userId;

    private LocalDate createdAt;

}
