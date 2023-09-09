package threekitties.tonghaeyo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToOne
    private Route route;

}
