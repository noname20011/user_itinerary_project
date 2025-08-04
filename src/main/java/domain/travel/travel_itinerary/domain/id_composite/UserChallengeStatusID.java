package domain.travel.travel_itinerary.domain.id_composite;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
public class UserChallengeStatusID implements Serializable {

    private UUID challenge;
    private UUID user;

    public UserChallengeStatusID(UUID challenge, UUID user) {
        this.user = user;
        this.challenge = challenge;
    }

    public UserChallengeStatusID() {}



}
