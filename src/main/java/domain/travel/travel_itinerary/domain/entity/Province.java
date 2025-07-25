package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="tbl_province")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Province extends BaseEntityHasId {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "code_name",nullable = false, length = 50)
    private String codeName;

    @OneToMany(mappedBy = "province", cascade = CascadeType.REMOVE, fetch =  FetchType.LAZY)
    @JsonManagedReference
    private List<Destination> destinations;
}
