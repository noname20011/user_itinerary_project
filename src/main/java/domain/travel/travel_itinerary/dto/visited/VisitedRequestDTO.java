package domain.travel.travel_itinerary.dto.visited;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import domain.travel.travel_itinerary.dto.visited_photo.VisitedPhotoRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Setter
public class VisitedRequestDTO implements Serializable {

    private UUID id;

    @NotNull(message = "{visited.destinationId.validate}")
    private UUID destinationId;

    @NotNull(message = "{visited.userId.validate}")
    private UUID userId;

    @NotNull(message = "{visited.visitedTime.validate}")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate visitedTime;

    private String reviewText;
    private String tag;
    private List<MultipartFile> photoFiles;
    private List<String> photosUrl;
}
