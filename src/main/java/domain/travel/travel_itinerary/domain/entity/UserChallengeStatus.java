package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.travel.travel_itinerary.domain.id_composite.UserChallengeStatusID;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityNoId;
import domain.travel.travel_itinerary.domain.enums.StatusProgressEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_user_challenge_status")
@IdClass(UserChallengeStatusID.class)
@Data
@EqualsAndHashCode(of = {"user", "challenge"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserChallengeStatus extends BaseEntityNoId {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    @JsonBackReference
    private Challenge challenge;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private  User user;

    @Column(name = "status", columnDefinition = "ENUM('IN_PROGRESS', 'COMPLETED')")
    @Enumerated(EnumType.STRING)
    private StatusProgressEnum status;

    @Column(name = "completed_at")
    private LocalDate completedAt;

}
