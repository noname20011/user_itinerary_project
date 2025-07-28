package domain.travel.travel_itinerary.controller;

import domain.travel.travel_itinerary.config.Translator;
import domain.travel.travel_itinerary.dto.visited.VisitedRequestDTO;
import domain.travel.travel_itinerary.dto.visited.VisitedResponseDTO;
import domain.travel.travel_itinerary.helper.base.dto.ResponseData;
import domain.travel.travel_itinerary.helper.base.dto.ResponseDataPageable;
import domain.travel.travel_itinerary.helper.validators.uuid_validation.ValidUUID;
import domain.travel.travel_itinerary.repository.custom_repository.PagingResult;
import domain.travel.travel_itinerary.service.VisitedService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/visited/")
@RequiredArgsConstructor
public class VisitedController {
    private final VisitedService visitedService;

    @PostMapping(value = "/")
    public ResponseData<?> addVisited(@ModelAttribute @Valid VisitedRequestDTO requestDTO) {
        VisitedResponseDTO response =  visitedService.addVisited(requestDTO);
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("visited.add.success"), response);
    }

    @PutMapping(value = "/{visitedId}")
    public ResponseData<?> updateVisited(@ValidUUID @PathVariable UUID visitedId,
                                             @ModelAttribute @Valid VisitedRequestDTO requestDTO) {
        VisitedResponseDTO response = visitedService.updateVisited(visitedId, requestDTO);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("visited.update.success"), response);
    }


    @DeleteMapping(value = "/{visitedId}")
    public ResponseData<?> deleteVisited(@ValidUUID @PathVariable UUID visitedId) {
        visitedService.deleteVisited(visitedId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("visited.delete.success"));
    }

    @DeleteMapping(value = "/batch")
    public ResponseData<?> deleteVisited(@RequestBody @Valid List<@ValidUUID UUID> visitedIds) {
        visitedService.deleteListVisited(visitedIds);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("visited.delete.success"));
    }

    @GetMapping(value = "/get-all-by-user/{userId}")
    public ResponseDataPageable<?> getAllVisited(@ValidUUID @PathVariable UUID userId, Pageable pageable) {
        PagingResult<VisitedResponseDTO> responseDTOS = visitedService.getAllVisitedByUserId(userId, pageable);
        return new ResponseDataPageable<>(
                HttpStatus.OK.value(),
                Translator.toLocale("visited.get.all.success"),
                responseDTOS.getData(), responseDTOS.getPage(), responseDTOS.getSize(), responseDTOS.getTotal());
    }

    @GetMapping(value = "/detail/{visitedId}")
    public ResponseData<?> getDetailVisited(@ValidUUID @PathVariable UUID visitedId) {
        VisitedResponseDTO response = visitedService.getVisited(visitedId);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("visited.get.detail.success"), response);
    }
}
