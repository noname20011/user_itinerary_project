package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.jdi.BooleanType;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_visited")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
//@SQLDelete(sql = "UPDATE tbl_visited SET deleted = true WHERE id = ?")
//@Filter(name = "deleteFilter", condition = "deleted = :isDeleted")
public class Visited extends BaseEntityHasId {

    @Column(name = "visited_time")
    private LocalDate visitedTime;

    @Column(name = "review_text", length = 9000)
    private String reviewText;

    @Column(name = "tag", length = 1000)
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    @JsonBackReference
    private Destination destination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "visited", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<VisitedPhoto> photos;

    @Column(name = "deleted")
    private boolean deleted = false;
}
