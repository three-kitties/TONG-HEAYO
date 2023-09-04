package threekitties.tonghaeyo.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import threekitties.tonghaeyo.domain.Authority;
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    public Long id;
    public Authority authority;
    public String name;

}