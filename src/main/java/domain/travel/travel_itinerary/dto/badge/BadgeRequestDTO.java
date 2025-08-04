package domain.travel.travel_itinerary.dto.badge;

import domain.travel.travel_itinerary.domain.enums.BadgeStatusEnum;
import domain.travel.travel_itinerary.dto.challenge.ChallengeRequestDTO;
import domain.travel.travel_itinerary.helper.validators.enum_validation.EnumPattern;
import domain.travel.travel_itinerary.helper.validators.uuid_validation.ValidUUID;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Setter
public class BadgeRequestDTO implements Serializable {

    @NotBlank(message = "")
    private String name;

    @EnumPattern(enumClass = BadgeStatusEnum.class, name = "Badge Status")
    private BadgeStatusEnum status;

    @NotNull(message = "")
    private MultipartFile thumbnailFile;

    private String conditionJson;

    private UUID typeBadgeId;

    @Valid
    private List<ChallengeRequestDTO> challenges;

    @Valid
    private List< @ValidUUID UUID> challengesId;
}
