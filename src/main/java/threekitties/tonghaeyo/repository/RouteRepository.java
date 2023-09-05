package threekitties.tonghaeyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.domain.Route;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> findByDriver(Member driver);
}
