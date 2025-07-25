package domain.travel.travel_itinerary.domain.entity;

import domain.travel.travel_itinerary.domain.enums.RoleEnum;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.stat.Statistics;

import java.util.List;

@Entity
@Table(name = "tbl_user")
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntityHasId {

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullName;

    @Column(name = "phone_number", unique = true, nullable = false, length = 11 )
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean active;

    @Column(name = "role", columnDefinition = "ENUM('ADMIN', 'CLIENT')")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Visited> visiteds;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<WhiteList> whiteLists;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserAchieveBadge> userAchieveBadges;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserChallengeProgress> userChallengeProgresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<LeaderBoard> leaderBoards;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Statistic statistic;

}
