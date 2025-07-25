package domain.travel.travel_itinerary.service.impl;

import domain.travel.travel_itinerary.domain.entity.Destination;
import domain.travel.travel_itinerary.domain.entity.Province;
import domain.travel.travel_itinerary.dto.province.ProvinceRequestDTO;
import domain.travel.travel_itinerary.dto.province.ProvinceResponseDTO;
import domain.travel.travel_itinerary.exception.DisabledException;
import domain.travel.travel_itinerary.exception.NotFoundException;
import domain.travel.travel_itinerary.mapper.ProvinceMapper;
import domain.travel.travel_itinerary.repository.ProvinceRepository;
import domain.travel.travel_itinerary.repository.custom_repository.province.PagingResult;
import domain.travel.travel_itinerary.service.ProvinceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;


    @Override
    public PagingResult<ProvinceResponseDTO> getAllProvinces(int page, int size) {
        return provinceRepository.getAllHasTotalDestination(page, size);
    }

    @Transactional
    @Override
    public ProvinceResponseDTO getProvince(UUID provinceId) {
        Province province = provinceRepository.findById(provinceId)
                .orElseThrow(() -> new NotFoundException("Province not found with id: " + provinceId));

//        Call lazy query get destinations is proxy, but not execute query
        List<Destination> destinations = province.getDestinations();
//        Execute query by .size()
        destinations.size();

        return provinceMapper.mapToResponseDto(province);
    }

    @Transactional
    @Override
    public List<ProvinceResponseDTO> addProvinces(List<ProvinceRequestDTO> data) {
        List<Province> provinces = provinceMapper.mapToListEntities(data);

//        CheckExistsProvince
        provinces.forEach(province -> {
            boolean isExists = provinceRepository.existsByName(province.getName());
            if (isExists) {
                throw new DisabledException("Province with name " + province.getName() + " already exists");
            }
        });

        List<Province> result = provinceRepository.saveAll(provinces);
        return provinceMapper.mapToListResponseDtos(result);
    }

    @Override
    public ProvinceResponseDTO addProvince(ProvinceRequestDTO data) {
        Province province = provinceMapper.mapToEntity(data);

        //        CheckExistsProvince
        boolean isExists = provinceRepository.existsByName(province.getName());
        if (isExists) {
            throw new DisabledException("Province with name " + province.getName() + " already exists");
        }

        Province result = provinceRepository.save(province);
        return provinceMapper.mapToResponseDto(result);
    }

    @Transactional
    @Override
    public List<ProvinceResponseDTO> updateProvinces(List<ProvinceRequestDTO> data) {

        List<Province> provinces = provinceMapper.mapToListEntities(data);
        System.out.print(provinces);
        List<Province> result = new ArrayList<>();
        provinces.forEach(province -> {
            result.add(provinceRepository.update(province.getId(), province));
        });
        return provinceMapper.mapToListResponseDtos(result);
    }


    @Override
    public ProvinceResponseDTO updateProvince(UUID provinceId, ProvinceRequestDTO data) {
        Province province = provinceMapper.mapToEntity(data);
        Province result = provinceRepository.update(provinceId, province);
        return provinceMapper.mapToResponseDto(result);
    }

    @Override
    public void deleteProvince(UUID provinceId) {
        provinceRepository.delete(provinceId);
    }
}
