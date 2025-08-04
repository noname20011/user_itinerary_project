package domain.travel.travel_itinerary.controller;

import domain.travel.travel_itinerary.config.Translator;
import domain.travel.travel_itinerary.dto.badge.BadgeRequestDTO;
import domain.travel.travel_itinerary.dto.badge.BadgeResponseDTO;
import domain.travel.travel_itinerary.helper.base.dto.ResponseData;
import domain.travel.travel_itinerary.helper.base.dto.ResponseDataPageable;
import domain.travel.travel_itinerary.repository.custom_repository.PagingResult;
import domain.travel.travel_itinerary.service.BadgeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/badge/")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping("/all/")
    public ResponseDataPageable<?> getAllBadges(Pageable pageable) {
        PagingResult<BadgeResponseDTO> response = badgeService.getAllBadges(pageable);
        return new ResponseDataPageable<>(
                HttpStatus.OK.value(),
                Translator.toLocale("asd"),
                response.getData(),
                response.getPage(),
                response.getSize(),
                response.getTotal()
        );
    }

    @GetMapping("/all/by-type-badge")
    public ResponseDataPageable<?> getAllBadgesByTypeBadge(@RequestParam("type-badge") UUID typeBadgeId, Pageable pageable) {
        PagingResult<BadgeResponseDTO> response = badgeService.getAllBadgesByTypeBadge(typeBadgeId, pageable);
        return new ResponseDataPageable<>(
                HttpStatus.OK.value(),
                Translator.toLocale("asd"),
                response.getData(),
                response.getPage(),
                response.getSize(),
                response.getTotal()
        );
    }

    @GetMapping("/detail/{:badgeId}")
    public ResponseData<?> getDetailBadge(@PathVariable UUID badgeId) {
        BadgeResponseDTO response = badgeService.getBadge(badgeId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("asd"), response);
    }

    @GetMapping("/all/for-user/")
    public ResponseDataPageable<?> getAllBadgesForUser(@RequestParam("user") UUID userId, Pageable pageable) {
        PagingResult<BadgeResponseDTO> response = badgeService.getAllBadgesByUser(userId, pageable);
        return new ResponseDataPageable<>(
                HttpStatus.OK.value(),
                Translator.toLocale("asd"),
                response.getData(),
                response.getPage(),
                response.getSize(),
                response.getTotal()
        );
    }

    @GetMapping("/all/by-type-badge/for-user/")
    public ResponseDataPageable<?> getAllBadgesByTypeBadgeForUser(
            @RequestParam("user") UUID userId,
            @RequestParam("type-badge") UUID typeBadgeId,
            Pageable pageable) {
        PagingResult<BadgeResponseDTO> response = badgeService.getAllBadgesByUserAndTypeBadge(userId, typeBadgeId, pageable);
        return new ResponseDataPageable<>(
                HttpStatus.OK.value(),
                Translator.toLocale("asd"),
                response.getData(),
                response.getPage(),
                response.getSize(),
                response.getTotal()
        );
    }

    @GetMapping("/detail/{:badgeId}/for-user/")
    public ResponseData<?> getDetailBadgeByForUser(
            @PathVariable UUID badgeId,
            @RequestParam("user") UUID userId,
            Pageable pageable) {
        BadgeResponseDTO response = badgeService.getDetailBadgeForUser(userId, badgeId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("asd"), response);
    }

    @PostMapping("/")
    public ResponseData<?> postBadge(@RequestBody @Valid BadgeRequestDTO badgeRequestDTO) {
        BadgeResponseDTO response = badgeService.addBadge(badgeRequestDTO);
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("asd"), response);
    }

    @PutMapping("/{:badgeId}")
    public ResponseData<?> updateBadge(@PathVariable UUID badgeId, @RequestBody @Valid BadgeRequestDTO badgeRequestDTO) {
        BadgeResponseDTO response = badgeService.updateBadge(badgeId, badgeRequestDTO);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("asd"), response);
    }

    @DeleteMapping("/{:badgeId}")
    public ResponseData<?> deleteBadge(@PathVariable UUID badgeId) {
        badgeService.deleteBadge(badgeId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("asd"));
    }
}
