package domain.travel.travel_itinerary.domain.entity;

import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityNoId;
import domain.travel.travel_itinerary.domain.enums.RankingLevelEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tbl_statistic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistic extends BaseEntityNoId {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_trips")
    private Integer totalTrips = 0;

    @Column(name = "total_destinations")
    private Integer totalDestinations = 0;

    @Column(name = "current_streak")
    private Integer currentStreak = 0;

    @Column(name = "ranking_level", columnDefinition = "ENUM ('NO_RANKING', 'SILVER', 'GOLD', 'PLATINUM', 'DIAMOND')")
    @Enumerated(EnumType.STRING)
    private RankingLevelEnum rankingLevel = RankingLevelEnum.NO_RANKING;

    @Column(name = "last_visited_date")
    private LocalDate lastVisitedDate;
}
