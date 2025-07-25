package domain.travel.travel_itinerary.controller;

import domain.travel.travel_itinerary.config.Translator;
import domain.travel.travel_itinerary.dto.province.ProvinceRequestDTO;
import domain.travel.travel_itinerary.dto.province.ProvinceResponseDTO;
import domain.travel.travel_itinerary.helper.base.dto.ResponseData;
import domain.travel.travel_itinerary.helper.base.dto.ResponseDataPageable;
import domain.travel.travel_itinerary.helper.utils.uuid_validation.ValidUUID;
import domain.travel.travel_itinerary.repository.custom_repository.province.PagingResult;
import domain.travel.travel_itinerary.service.ProvinceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/province/")
@RequiredArgsConstructor
@Slf4j
public class ProvinceController {

    private final ProvinceService provinceService;

    @PostMapping("/")
    public ResponseData<?> addProvince(@RequestBody @Valid ProvinceRequestDTO provinceRequestDTO) {
        ProvinceResponseDTO result = provinceService.addProvince(provinceRequestDTO);
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("province.add.success"), result);
    }

    @PostMapping("/batch")
    public ResponseData<?> addProvinces(@RequestBody @Valid List<@Valid ProvinceRequestDTO> provinceRequestDTOS) {
        List<ProvinceResponseDTO> results = provinceService.addProvinces(provinceRequestDTOS);
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("provinces.add.success"), results);
    }

    @PutMapping("/update/batch")
    public ResponseData<?> updateProvinces(@RequestBody @Valid List<@Valid ProvinceRequestDTO> provinceRequestDTOS) {
        List<ProvinceResponseDTO> results = provinceService.updateProvinces(provinceRequestDTOS);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), Translator.toLocale("provinces.update.success"), results);
//        List<ProvinceResponseDTO> results = provinceService.addProvinces(provinceRequestDTOS);
//        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("provinces.add.success"), results);
    }
//    public ResponseData<?> updateProvinces(@RequestBody @Valid List<@Valid ProvinceRequestDTO> provinceRequestDTOS) {
//        provinceRequestDTOS.forEach(province -> {
//            log.info("Updating province: {}", province.getName());
//        });
//        List<ProvinceResponseDTO> results = provinceService.updateProvinces(provinceRequestDTOS);
//        return new ResponseData<>(HttpStatus.ACCEPTED.value(), Translator.toLocale("provinces.update.success"), results);
//    }

    @PutMapping("/update/{provinceId}")
    public ResponseData<?> updateProvince(@RequestBody @Valid ProvinceRequestDTO provinceRequestDTO, @PathVariable @ValidUUID UUID provinceId) {
        ProvinceResponseDTO result = provinceService.updateProvince(provinceId, provinceRequestDTO);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), Translator.toLocale("province.update.success"), result);
    }

    @DeleteMapping("/delete/{provinceId}")
    public ResponseData<?> deleteProvince(@PathVariable @ValidUUID UUID provinceId) {
        provinceService.deleteProvince(provinceId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), Translator.toLocale("province.delete.success"));
    }

    @GetMapping("/all")
    public ResponseDataPageable<List<ProvinceResponseDTO>> getAllProvinces(@Min(1) int page, @Min(1) int size) {
        PagingResult<ProvinceResponseDTO> results = provinceService.getAllProvinces(page, size);
        return new ResponseDataPageable<>(HttpStatus.OK.value(),
                Translator.toLocale("province.all.success"),
                results.getData(), results.getPage(), results.getSize(), results.getTotal());
    }

    @GetMapping("/{provinceId}")
    public ResponseData<?> getProvince(@PathVariable @ValidUUID UUID provinceId) {
        ProvinceResponseDTO result = provinceService.getProvince(provinceId);
        result.setDestinations(null);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("province.detail.success"), result);
    }
}
