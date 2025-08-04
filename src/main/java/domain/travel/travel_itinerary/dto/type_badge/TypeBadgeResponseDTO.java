package domain.travel.travel_itinerary.dto.type_badge;

import com.fasterxml.jackson.annotation.JsonInclude;
import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import domain.travel.travel_itinerary.dto.badge.BadgeResponseDTO;
import domain.travel.travel_itinerary.dto.destination.DestinationResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class TypeBadgeResponseDTO implements Serializable {
    private UUID id;
    private String name;
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime createdAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updatedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<BadgeResponseDTO> badges;

}
