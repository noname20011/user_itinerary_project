package domain.travel.travel_itinerary.helper.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ErrorGlobalResponse {
    private Date date;
    private Integer status;
    private String error;
    private String path;
    private String message;
}
