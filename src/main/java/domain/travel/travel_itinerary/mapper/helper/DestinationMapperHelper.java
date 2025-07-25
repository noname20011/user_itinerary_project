package domain.travel.travel_itinerary.mapper.helper;

import domain.travel.travel_itinerary.domain.entity.Province;
import domain.travel.travel_itinerary.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DestinationMapperHelper {

    private final ProvinceRepository provinceRepository;

    @Named("getProvinceById")
    public Province getProvinceById(UUID provinceId) {
        return provinceId == null ? null : provinceRepository.findByIdOrThrow(provinceId);
    }
}
