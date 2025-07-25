package domain.travel.travel_itinerary.dto.learder_board;

import domain.travel.travel_itinerary.domain.entity.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class LeaderBoardResponseDTO implements Serializable {

    private UUID id;
    private User user;
    private String type;
    private Integer rankingLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
