package domain.travel.travel_itinerary.dto.statistic;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.domain.enums.RankingLevelEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@Setter
public class StatisticRequestDTO implements Serializable {

    @NotNull
    private User user;

    private Integer totalTrips;

    private Integer totalDestinations;

    private Integer currentStreak;

    private RankingLevelEnum rankingLevel;

    private LocalDate lastVisitedDate;
}
