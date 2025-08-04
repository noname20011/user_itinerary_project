package domain.travel.travel_itinerary.dto.challenge;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@Setter
public class ChallengeRequestDTO implements Serializable {

    private UUID id;

    @NotNull(message = "")
    private String name;

    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "")
    private LocalDate endDate;

    private String conditionJson;
}
