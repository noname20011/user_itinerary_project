package domain.travel.travel_itinerary.dto.user;

import domain.travel.travel_itinerary.domain.enums.RoleEnum;
import domain.travel.travel_itinerary.helper.utils.enum_validation.EnumPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Builder
@Setter
public class UserRequestDTO implements Serializable {

    @NotBlank(message = "Field FullName must be not blank!")
    private String fullName;

    @Pattern(
            regexp = "^(0|\\+84)[3|5|7|9][0-9]{8}$",
            message = "PhoneNumber not valid!"
    )
    private String phoneNumber;

    @NotBlank(message = "Field Password must be not blank!")
    private String password;

    @NotNull
    private Boolean active;

    @EnumPattern(name = "Role", enumClass = RoleEnum.class)
    private RoleEnum role;

}
