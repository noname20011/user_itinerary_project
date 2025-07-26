package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "tbl_visited_photo")
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@SQLDelete(sql = "UPDATE tbl_visited_photo SET deleted = true WHERE id = ?")
//@Filter(name = "deleteFilter", condition = "deleted = :isDeleted")
public class VisitedPhoto extends BaseEntityHasId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="visited_id", nullable = false)
    @JsonBackReference
    private Visited visited;

    @Column(name = "photoUrl")
    private String photoUrl;

    @Column(name = "tag")
    private String tag;

    @Column(name = "description", length = 9000)
    private String reviewText;

    @Column(name = "deleted")
    private boolean deleted = false;
}
