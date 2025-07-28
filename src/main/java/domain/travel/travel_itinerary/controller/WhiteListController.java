package domain.travel.travel_itinerary.controller;

import domain.travel.travel_itinerary.config.Translator;
import domain.travel.travel_itinerary.dto.whitelist.WhiteListRequestDTO;
import domain.travel.travel_itinerary.dto.whitelist.WhiteListResponseDTO;
import domain.travel.travel_itinerary.helper.base.dto.ResponseData;
import domain.travel.travel_itinerary.helper.validators.uuid_validation.ValidUUID;
import domain.travel.travel_itinerary.service.WhiteListService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/whitelist/")
@RequiredArgsConstructor
public class WhiteListController {
    private final WhiteListService whiteListService;

    @PostMapping(value = "/")
    public ResponseData<?> addWhiteList(@RequestBody @Valid WhiteListRequestDTO requestDTO) {
        WhiteListResponseDTO response =  whiteListService.addWhiteList(requestDTO);
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("whitelist.add.success"), response);
    }

    @PostMapping(value = "/batch/")
    public ResponseData<?> addWhiteLists(@RequestBody @Valid List<@Valid WhiteListRequestDTO> requestDTOS) {
        List<WhiteListResponseDTO> response =  whiteListService.addWhiteLists(requestDTOS);
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("whitelist.add.success"), response);
    }


    @DeleteMapping(value = "/{whiteListId}")
    public ResponseData<?> deleteWhiteList(@ValidUUID @PathVariable UUID whiteListId) {
        whiteListService.deleteWhiteList(whiteListId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("whitelist.delete.success"));
    }

    @DeleteMapping(value = "/batch")
    public ResponseData<?> deleteWhiteLists(@RequestBody @Valid List<@ValidUUID UUID> ids) {
        whiteListService.deleteWhiteLists(ids);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("visited.delete.success"));
    }

    @GetMapping(value = "/get-all-by-user/{userId}")
    public ResponseData<?> getAllWhiteList(@ValidUUID @PathVariable UUID userId) {
       List<WhiteListResponseDTO> responseDTOS = whiteListService.getAllWhiteListByUser(userId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("whitelist.get.all.success"), responseDTOS);
    }

    @GetMapping(value = "/detail/{whiteListId}")
    public ResponseData<?> getDetailWhiteList(@ValidUUID @PathVariable UUID whiteListId) {
        WhiteListResponseDTO response = whiteListService.getDetailWhiteList(whiteListId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("whitelist.get.detail.success"), response);
    }
}
