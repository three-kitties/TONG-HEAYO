package threekitties.tonghaeyo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Member driver;

    @OneToOne
    private Location startPoint;

    @OneToMany
    private List<Location> stopovers;

    @OneToOne
    private Location destination;

    public Route(Member driver) {
        this.driver = driver;
        this.startPoint = null;
        this.stopovers = new ArrayList<>();
        this.destination = null;
    }

    public Route addStartPoint(Location startPoint) {
        this.startPoint = startPoint;

        return this;
    }

    public Route addStopovers(Location location) {
        this.stopovers.add(location);

        return this;
    }

    public Route addDestination(Location destination) {
        this.destination = destination;

        return this;
    }

}
