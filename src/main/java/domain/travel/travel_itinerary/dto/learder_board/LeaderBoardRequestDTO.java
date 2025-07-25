package domain.travel.travel_itinerary.dto.learder_board;

import domain.travel.travel_itinerary.domain.entity.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@Setter
public class LeaderBoardRequestDTO implements Serializable {

    private User user;
    private String type;
    private Integer rankingLevel;
}
