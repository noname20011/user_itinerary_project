package domain.travel.travel_itinerary.dto.whitelist;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import domain.travel.travel_itinerary.helper.validators.uuid_validation.ValidUUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@Setter
public class WhiteListRequestDTO implements Serializable {

    @ValidUUID(message = "{whitelist.destinationId.validate.uuid}")
    @NotNull(message = "{whitelist.destinationId.validate}")
    private UUID destinationId;

    @ValidUUID(message = "{whitelist.userId.validate.uuid}")
    @NotNull(message = "{whitelist.userId.validate}")
    private UUID userId;

}
