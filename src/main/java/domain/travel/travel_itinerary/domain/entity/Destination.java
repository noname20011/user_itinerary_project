package domain.travel.travel_itinerary.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonMerge;
import domain.travel.travel_itinerary.domain.enums.DestinationStatusEnum;
import domain.travel.travel_itinerary.helper.base.entiry.BaseEntityHasId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="tbl_destination")
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Destination extends BaseEntityHasId {

    @Column(name = "name", nullable = false, length = 400)
    private String name;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "province_id", nullable = false)
    @JsonBackReference
    private Province province;

    @Column(name = "status", columnDefinition = "ENUM('DANGEROUS','BAD_WEATHER','WARNING','BEAUTIFUL')")
    @Enumerated(EnumType.STRING)
    private DestinationStatusEnum status;

    @Column(name = "status_message")
    private String statusMessage;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<DestinationPhotos> photos;

    @OneToMany(mappedBy = "destination")
    @JsonManagedReference
    private List<Visited> visiteds;

    @OneToMany(mappedBy = "destination")
    @JsonManagedReference
    private List<WhiteList> whiteLists;

//    public Destination setWithId (UUID id) {
//        this.setId(id);
//        return this;
//    }
}
