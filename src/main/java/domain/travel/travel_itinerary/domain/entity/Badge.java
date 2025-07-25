package domain.travel.travel_itinerary.domain.entity;

import domain.travel.travel_itinerary.domain.enums.BadgeStatusEnum;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tbl_badge")
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Badge extends BaseEntityHasId {

    @Column(name = "name", nullable = false, length = 400)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "status", columnDefinition = "ENUM('ENDED', 'ACTIVATING', 'COMING_SOON')")
    @Enumerated(EnumType.STRING)
    private BadgeStatusEnum status;

    @OneToMany(mappedBy = "badge", fetch = FetchType.EAGER)
    List<Challenge> challenges;

    @OneToMany(mappedBy = "badge")
    List<UserAchieveBadge> userAchieveBadges;

}
