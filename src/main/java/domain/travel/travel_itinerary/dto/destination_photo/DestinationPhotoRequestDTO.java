package domain.travel.travel_itinerary.dto.destination_photo;

import domain.travel.travel_itinerary.helper.dto.FilePhotoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class DestinationPhotoRequestDTO implements Serializable {

    private UUID id;

    private UUID destinationId;

    private String photoUrl;

    private FilePhotoDTO filePhoto;

    public DestinationPhotoRequestDTO (UUID destinationId, FilePhotoDTO filePhoto  ) {
        this.filePhoto = filePhoto;
        this.destinationId = destinationId;
    }

    public DestinationPhotoRequestDTO (UUID destinationId, String photoUrl) {
        this.photoUrl = photoUrl;
        this.destinationId = destinationId;
    }
}
