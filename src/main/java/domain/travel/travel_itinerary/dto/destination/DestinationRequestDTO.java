package domain.travel.travel_itinerary.dto.destination;

import domain.travel.travel_itinerary.domain.enums.DestinationStatusEnum;
import domain.travel.travel_itinerary.helper.dto.FilePhotoDTO;
import domain.travel.travel_itinerary.helper.validators.enum_validation.EnumPattern;
import domain.travel.travel_itinerary.helper.validators.uuid_validation.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Setter
public class DestinationRequestDTO implements Serializable {

    private UUID id;

    @NotBlank(message = "{destination.name.validate}")
    private String name;

    @NotBlank(message = "{destination.description.validate}")
    private String description;

    @NotNull(message = "{destination.latitude.validate}")
    private double latitude;

    @NotNull(message = "{destination.longitude.validate}")
    private double longitude;

    @EnumPattern(enumClass = DestinationStatusEnum.class, message = "{destination.status.validate}", name = "DestinationStatusEnum")
    private DestinationStatusEnum status;

    private String statusMessage;

    @ValidUUID(message = "{destination.provinceId.validate}")
    private UUID provinceId;

    private List<MultipartFile> filePhotos;
    private List<String> photosUrl;
}
