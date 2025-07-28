package domain.travel.travel_itinerary.service;

import domain.travel.travel_itinerary.dto.province.ProvinceRequestDTO;
import domain.travel.travel_itinerary.dto.province.ProvinceResponseDTO;
import domain.travel.travel_itinerary.repository.custom_repository.PagingResult;

import java.util.List;
import java.util.UUID;

public interface ProvinceService {
    PagingResult<ProvinceResponseDTO> getAllProvinces(int page, int size);
    ProvinceResponseDTO getProvince(UUID provinceId);

    List<ProvinceResponseDTO> addProvinces(List<ProvinceRequestDTO> data);
    ProvinceResponseDTO addProvince(ProvinceRequestDTO data);

    List<ProvinceResponseDTO> updateProvinces(List<ProvinceRequestDTO> data);
    ProvinceResponseDTO updateProvince(UUID provinceId, ProvinceRequestDTO data);

    void deleteProvince(UUID provinceId);

}
