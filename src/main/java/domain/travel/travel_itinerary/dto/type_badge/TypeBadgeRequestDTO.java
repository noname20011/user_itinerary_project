package domain.travel.travel_itinerary.dto.type_badge;

import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Setter
public class TypeBadgeRequestDTO implements Serializable {

    private UUID id;

    @NotBlank(message = "{province.name.validate}")
    private String name;

    @NotBlank(message = "{province.codeName.validate}")
    private String code;

    List<BadgeRequestDTO> badgeRequestDTOList;
}
