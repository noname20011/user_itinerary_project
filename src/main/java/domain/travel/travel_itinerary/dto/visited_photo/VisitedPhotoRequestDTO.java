package domain.travel.travel_itinerary.dto.visited_photo;

import domain.travel.travel_itinerary.helper.dto.FilePhotoDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class VisitedPhotoRequestDTO implements Serializable {

    private UUID id;

    @NotNull(message = "{visited.destinationId.validate}")
    private UUID visitedId;

    private String reviewText;

    private String tag;

    private FilePhotoDTO filePhoto;

    private String photoUrl;

    public VisitedPhotoRequestDTO (UUID visitedId, FilePhotoDTO filePhoto  ) {
        this.filePhoto = filePhoto;
        this.visitedId = visitedId;
    }

    public VisitedPhotoRequestDTO (UUID visitedId, String photoUrl) {
        this.photoUrl = photoUrl;
        this.visitedId = visitedId;
    }
}
