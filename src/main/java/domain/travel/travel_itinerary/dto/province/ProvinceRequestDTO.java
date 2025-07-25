package domain.travel.travel_itinerary.dto.province;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
@Setter
public class ProvinceRequestDTO implements Serializable {

    private UUID id;

    @NotBlank(message = "{province.name.validate}")
    private String name;

    @NotBlank(message = "{province.codeName.validate}")
    private String codeName;
}
