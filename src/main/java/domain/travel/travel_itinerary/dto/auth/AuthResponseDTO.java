package domain.travel.travel_itinerary.dto.auth;

import domain.travel.travel_itinerary.dto.user.UserResponseDTO;
import domain.travel.travel_itinerary.helper.base.dto.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthResponseDTO extends ResponseData<UserResponseDTO> {
    private String accessToken;

    public AuthResponseDTO(int status, String message, UserResponseDTO data, String accessToken) {
        super(status, message, data);
        this.accessToken = accessToken;
    }
}
