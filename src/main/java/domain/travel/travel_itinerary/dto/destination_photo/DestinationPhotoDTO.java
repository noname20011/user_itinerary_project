package domain.travel.travel_itinerary.dto.destination_photo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

    @Getter
    @Setter
    public class DestinationPhotoDTO {
        private UUID id;
        private String url;
        private MultipartFile newPhoto;
    }
