package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tbl_type_badge")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"} , callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class TypeBadge  extends BaseEntityHasId {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @OneToMany(mappedBy = "typeBadge")
    @JsonManagedReference
    private Set<Badge> badges;
}
