package domain.travel.travel_itinerary.helper.base.entiry;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseEntityHasId extends BaseEntityNoId {

    @Column(name = "id", updatable = false, nullable = false)
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
}
