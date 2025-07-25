package domain.travel.travel_itinerary.domain.id_composite;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
public class UserBadgeProgressID implements Serializable {

    private UUID user;
    private UUID badge;

    public UserBadgeProgressID(UUID user, UUID badge) {
        this.user = user;
        this.badge = badge;
    }

    public UserBadgeProgressID() {}


}
