package domain.travel.travel_itinerary.helper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class FilePhotoDTO {
    private MultipartFile filePhoto;
}
