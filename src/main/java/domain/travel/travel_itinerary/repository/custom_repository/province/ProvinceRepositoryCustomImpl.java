package domain.travel.travel_itinerary.repository.custom_repository.province;

import domain.travel.travel_itinerary.domain.entity.Destination;
import domain.travel.travel_itinerary.domain.entity.Province;
import domain.travel.travel_itinerary.dto.province.ProvinceResponseDTO;
import domain.travel.travel_itinerary.repository.custom_repository.PagingResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

@Repository
public class ProvinceRepositoryCustomImpl implements ProvinceRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public PagingResult<ProvinceResponseDTO> getAllHasTotalDestination(int page, int size) {
//        Get factoryObject to begin execute query
        CriteriaBuilder cb = em.getCriteriaBuilder();

//        Equal Select .... to response
        CriteriaQuery<ProvinceResponseDTO> query = cb.createQuery(ProvinceResponseDTO.class);

//        Equal from...
        Root<Province> province = query.from(Province.class);

//        Equal (left) join ... on ..
        Join<Province, Destination> destination = province.join("destinations", JoinType.LEFT);

//        Equal add and im join (and destination.province_id = :provinceId)
//        destination.on(cb.equal(destination.get("province").get("id"), provinceId));

//        Select province.*, totalDestination
        query.select(cb.construct(ProvinceResponseDTO.class,
                province.get("id"),
                province.get("name"),
                province.get("codeName"),
                cb.count(destination.get("id"))
        )).groupBy(province.get("id"));

//        Pageable
        TypedQuery<ProvinceResponseDTO> result = em.createQuery(query);
        result.setFirstResult((page - 1) * size);
        result.setMaxResults(size);


        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Province> queryRoot = countQuery.from(Province.class);
        countQuery.select(cb.count(queryRoot));
        Long totalCount = em.createQuery(countQuery).getSingleResult();


        return new PagingResult<>(page, size, totalCount, result.getResultList());
    }
}
