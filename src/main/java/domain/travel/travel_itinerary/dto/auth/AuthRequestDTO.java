package domain.travel.travel_itinerary.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRequestDTO {

    @Pattern(
            regexp = "^(0|\\+84)[3|5|7|9][0-9]{8}$",
            message = "Phone number not valid!"
    )
    private String phoneNumber;

    @NotBlank(message = "Field password must be not blank")
    private String password;
}
