package threekitties.tonghaeyo.domain;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Location {

    private Long lat;
    private Long lng;

    public Location(Long lat, Long lng) {
        this.lat = lat;
        this.lng = lng;
    }

}
