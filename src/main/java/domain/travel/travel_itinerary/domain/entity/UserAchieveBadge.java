package domain.travel.travel_itinerary.domain.entity;

import domain.travel.travel_itinerary.domain.id_composite.UserBadgeProgressID;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityNoId;
import domain.travel.travel_itinerary.domain.enums.StatusProgressEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user_achieve_badge")
@IdClass(UserBadgeProgressID.class)
@Data
@EqualsAndHashCode(of = {"user", "badge"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserAchieveBadge extends BaseEntityNoId {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id", nullable = false)
    private Badge badge;

    @Column(name = "status", columnDefinition = "ENUM('PROGRESSING', 'COMPLETED')")
    @Enumerated(EnumType.STRING)
    private StatusProgressEnum status;

    @Column(name = "progress_value")
    private Integer progressValue = 0;

}
