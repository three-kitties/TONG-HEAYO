package threekitties.tonghaeyo.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
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

    @Nullable
    @ManyToOne
    private Organization organization;

}