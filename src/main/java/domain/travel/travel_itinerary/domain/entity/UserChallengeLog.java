package domain.travel.travel_itinerary.domain.entity;

import domain.travel.travel_itinerary.domain.enums.ChallengeActionEnum;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import domain.travel.travel_itinerary.helper.converter.JsonMapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Table(name = "tbl_user_challenge_log")
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserChallengeLog extends BaseEntityHasId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Column(name = "action", columnDefinition = "ENUM('STARTED', 'CHECKIN_LOCATION', 'SESSION_RECORDED', 'STEP_RECORDED', 'ANSWER_SUBMITTED', 'COMPLETED')")
    @Enumerated(EnumType.STRING)
    private ChallengeActionEnum action;

    @Convert(converter = JsonMapConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> metadata;
}
