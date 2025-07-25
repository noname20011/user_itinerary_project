package domain.travel.travel_itinerary.dto.whitelist;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@Setter
public class WhiteListRequestDTO implements Serializable {

    @NotNull
    private DestinationRequestDTO destination;

    @NotNull
    private User user;

    @NotNull
    private LocalDate addDate;

}
