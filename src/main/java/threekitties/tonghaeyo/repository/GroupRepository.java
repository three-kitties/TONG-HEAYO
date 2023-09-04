package threekitties.tonghaeyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import threekitties.tonghaeyo.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
