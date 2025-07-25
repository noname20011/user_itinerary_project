package domain.travel.travel_itinerary.dto.badge;

import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@Setter
public class BadgeRequestDTO implements Serializable {

    private String name;
    private String description;
    private String thumbnail;
}
