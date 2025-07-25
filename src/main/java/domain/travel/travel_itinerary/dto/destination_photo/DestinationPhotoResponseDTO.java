package domain.travel.travel_itinerary.dto.destination_photo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
public class DestinationPhotoResponseDTO implements Serializable {

    private UUID id;
    private UUID destinationId;
    private String photoUrl;

}
