package domain.travel.travel_itinerary.domain.entity;

import domain.travel.travel_itinerary.domain.id_composite.UserChallengeProgressID;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityNoId;
import domain.travel.travel_itinerary.domain.enums.StatusProgressEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user_challenge_progress")
@IdClass(UserChallengeProgressID.class)
@Data
@EqualsAndHashCode(of = {"user", "challenge"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserChallengeProgress extends BaseEntityNoId {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private  User user;

    @Column(name = "status", columnDefinition = "ENUM('PROGRESSING', 'COMPLETED')")
    @Enumerated(EnumType.STRING)
    private StatusProgressEnum status;

    @Column(name = "progress_value")
    private Integer progressValue = 0;
}
