package threekitties.tonghaeyo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import threekitties.tonghaeyo.domain.Member;
import threekitties.tonghaeyo.domain.Route;
import threekitties.tonghaeyo.repository.RouteRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public Route save(Route route) {
        return routeRepository.save(route);
    }

    public Route findByDriver(Member driver) {
        Optional<Route> opRoute = routeRepository.findByDriver(driver);

        return opRoute.orElseGet(() -> routeRepository.save(new Route(driver)));
    }

}
