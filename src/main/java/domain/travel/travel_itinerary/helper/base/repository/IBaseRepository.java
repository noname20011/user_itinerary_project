package domain.travel.travel_itinerary.helper.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;


public interface IBaseRepository<T, ID extends Serializable>{

    T findByIdOrThrow(ID id);

    List<T> findAllSorted(Sort sort);

    Page<T> paginate(Pageable pageable);

    List<T> getAll();

    T save(T entity);

    T save(T entity, Function<T, T> callback);

    T update(ID id, T entity);

    T update(ID id, T entity, Function<T, T> callback);

    T delete(ID id);

    T delete(ID id, Function<T, T> callback);

}
