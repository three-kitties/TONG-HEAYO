package threekitties.tonghaeyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import threekitties.tonghaeyo.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
