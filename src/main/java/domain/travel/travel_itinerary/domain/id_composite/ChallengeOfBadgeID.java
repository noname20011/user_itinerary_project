package domain.travel.travel_itinerary.domain.id_composite;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeOfBadgeID {
    private UUID challenge;
    private UUID badge;
}
