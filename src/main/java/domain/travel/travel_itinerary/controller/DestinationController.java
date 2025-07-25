package domain.travel.travel_itinerary.controller;

import domain.travel.travel_itinerary.config.Translator;
import domain.travel.travel_itinerary.dto.destination.DestinationRequestDTO;
import domain.travel.travel_itinerary.dto.destination.DestinationResponseDTO;
import domain.travel.travel_itinerary.helper.base.dto.ResponseData;
import domain.travel.travel_itinerary.helper.base.dto.ResponseDataPageable;
import domain.travel.travel_itinerary.helper.utils.uuid_validation.ValidUUID;
import domain.travel.travel_itinerary.repository.custom_repository.province.PagingResult;
import domain.travel.travel_itinerary.service.DestinationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/destination/")
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping(value = "/")
    public ResponseData<?> addDestination(@ModelAttribute @Valid DestinationRequestDTO requestDTO) {
        DestinationResponseDTO response =  destinationService.addDestination(requestDTO);
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("destination.add.success"), response);
    }

    @PostMapping(value = "/batch/")
    public ResponseData<?> addDestinations(@ModelAttribute @Valid List<@Valid DestinationRequestDTO> requestDTOS) {
        List<DestinationResponseDTO> response =  destinationService.addDestinations(requestDTOS);
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("destinations.add.success"), response);
    }

    @PutMapping(value = "/{provinceId}")
    public ResponseData<?> updateDestination(@ValidUUID @PathVariable UUID provinceId,
                                             @ModelAttribute @Valid DestinationRequestDTO requestDTO) {
        DestinationResponseDTO response =  destinationService.updateDestination(provinceId, requestDTO);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("destination.update.success"), response);
    }

    @PutMapping(value = "/batch/{provinceId}")
    public ResponseData<?> updateDestination(@ValidUUID @PathVariable UUID provinceId,
            @ModelAttribute @Valid List<@Valid DestinationRequestDTO> requestDTOS) {
        List<DestinationResponseDTO> response =  destinationService.updateDestinations(provinceId, requestDTOS);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("destinations.update.success"), response);
    }

    @DeleteMapping(value = "/{destinationId}")
    public ResponseData<?> deleteDestination(@ValidUUID @PathVariable UUID destinationId) {
        destinationService.deleteDestination(destinationId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("destination.delete.success"));
    }

    @DeleteMapping(value = "/batch")
    public ResponseData<?> deleteDestination(@RequestBody @Valid List<@ValidUUID UUID> destinationIds) {
        destinationService.deleteDestinations(destinationIds);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("destinations.delete.success"));
    }

    @GetMapping(value = "/get-all-by-province/{provinceId}")
    public ResponseDataPageable<?> getAllDestination(@ValidUUID @PathVariable UUID provinceId, Pageable pageable) {
        PagingResult<DestinationResponseDTO> responseDTOS = destinationService.getAllDestinationByProvince(provinceId, pageable);
        return new ResponseDataPageable<>(
                HttpStatus.OK.value(),
                Translator.toLocale("destination.get.all.success"),
                responseDTOS.getData(), responseDTOS.getPage(), responseDTOS.getSize(), responseDTOS.getTotal());
    }

    @GetMapping(value = "/detail/{destinationId}")
    public ResponseData<?> getDetailDestination(@ValidUUID @PathVariable UUID destinationId) {
        DestinationResponseDTO response = destinationService.getDestination(destinationId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("destination.get.detail.success"), response);
    }
}
