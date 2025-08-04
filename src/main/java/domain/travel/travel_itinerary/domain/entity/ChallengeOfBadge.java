package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.travel.travel_itinerary.domain.id_composite.ChallengeOfBadgeID;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityNoId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_challenge_of_badge")
@IdClass(ChallengeOfBadgeID.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeOfBadge extends BaseEntityNoId {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id")
    @JsonBackReference
    private Badge badge;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    @JsonBackReference
    private Challenge challenge;
}
