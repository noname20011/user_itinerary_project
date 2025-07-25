package domain.travel.travel_itinerary.dto.whitelist;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class WhiteListResponseDTO implements Serializable {

    @NotNull
    private UUID id;

    @NotNull
    private DestinationRequestDTO destination;

    @NotNull
    private User user;

    @NotNull
    private LocalDate addDate;

}
