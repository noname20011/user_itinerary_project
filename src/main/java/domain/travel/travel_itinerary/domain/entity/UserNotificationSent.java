package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.travel.travel_itinerary.domain.enums.NotificationTypeEnum;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_user_notified_sent")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationSent extends BaseEntityHasId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "reference_id")
    private UUID referenceId;

    @Column(name = "notification_type", columnDefinition = "ENUM('NEAR_BY', 'ACHIEVE_BADGE', 'ACHIEVE_CHALLENGE')")
    @Enumerated(EnumType.STRING)
    private NotificationTypeEnum notificationType;

    @Column(name = "is_read")
    private Boolean isRead;

}
