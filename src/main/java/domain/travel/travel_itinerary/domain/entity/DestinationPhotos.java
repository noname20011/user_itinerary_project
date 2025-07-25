package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonMerge;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_destination_photo")
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DestinationPhotos extends BaseEntityHasId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destination_id", nullable = false)
    @JsonBackReference
    private Destination destination;

    @Column(name = "photoUrl")
    private String photoUrl;
}
