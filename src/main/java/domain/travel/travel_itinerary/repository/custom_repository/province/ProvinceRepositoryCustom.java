package domain.travel.travel_itinerary.repository.custom_repository.province;

import domain.travel.travel_itinerary.dto.province.ProvinceResponseDTO;

public interface ProvinceRepositoryCustom {

    PagingResult<ProvinceResponseDTO> getAllHasTotalDestination(int page, int size);
}
