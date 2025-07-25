package domain.travel.travel_itinerary.dto.visited;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@Setter
public class VisitedRequestDTO implements Serializable {

    @NotNull
    private DestinationRequestDTO destinationId;

    @NotNull
    private UUID user;

    @NotNull(message = "Field VisitedTime must be not null!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate visitedTime;

    private String reviewText;

    private Integer rating;

    private String photoUrl;
}
