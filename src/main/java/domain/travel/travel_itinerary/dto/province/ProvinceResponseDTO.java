package domain.travel.travel_itinerary.dto.province;

import com.fasterxml.jackson.annotation.JsonInclude;
import domain.travel.travel_itinerary.dto.destination.DestinationResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class ProvinceResponseDTO implements Serializable {
    private UUID id;
    private String name;
    private String codeName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalDestination;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DestinationResponseDTO> destinations;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime createdAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updatedAt;

    public ProvinceResponseDTO(UUID id, String name, String codeName, Long totalDestination) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.totalDestination = (totalDestination != null) ? totalDestination : 0;
    }

    public ProvinceResponseDTO(UUID id, String name, String codeName, List<DestinationResponseDTO> destinations) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.totalDestination = (long) destinations.size();
        this.destinations = destinations;
    }

}
