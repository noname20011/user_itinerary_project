package domain.travel.travel_itinerary.repository;

import domain.travel.travel_itinerary.domain.entity.User;
import domain.travel.travel_itinerary.helper.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPassword(String password);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
