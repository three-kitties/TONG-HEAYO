package threekitties.tonghaeyo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private Authority authority;
    private String name;

    @ManyToOne
    private Organization organization;

}