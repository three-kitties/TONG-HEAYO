package threekitties.tonghaeyo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    private Long lat;
    private Long lng;

    public Location(Long lat, Long lng) {
        this.lat = lat;
        this.lng = lng;
    }

}
