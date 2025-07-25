package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.Province;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import domain.travel.travel_itinerary.repository.custom_repository.province.ProvinceRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvinceRepository extends BaseRepository<Province, UUID>, ProvinceRepositoryCustom {
    boolean existsByName(String name);
}
