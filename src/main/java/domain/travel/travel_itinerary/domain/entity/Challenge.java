package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_challenge")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Challenge extends BaseEntityHasId {

    @Column(name = "title", nullable = false, length = 400)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "condition_json")
    private String conditionJson;

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ChallengeOfBadge> challengeOfBadges;

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<UserChallengeStatus> userChallengeStatuses;

}
