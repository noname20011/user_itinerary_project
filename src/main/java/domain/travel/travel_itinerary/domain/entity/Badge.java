package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.travel.travel_itinerary.domain.enums.BadgeStatusEnum;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_badge")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Badge extends BaseEntityHasId {

    @Column(name = "name", nullable = false, length = 400)
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "status", columnDefinition = "ENUM('ENDED', 'ACTIVATING', 'COMING_SOON')")
    @Enumerated(EnumType.STRING)
    private BadgeStatusEnum status;

    @Column(name = "condition_json")
    private String conditionJson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_badge_id")
    @JsonBackReference
    private TypeBadge typeBadge;

    @OneToMany(mappedBy = "badge", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<ChallengeOfBadge> challengeOfBadges;

    @OneToMany(mappedBy = "badge", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<UserAchieveBadge> userAchieveBadges;


}
