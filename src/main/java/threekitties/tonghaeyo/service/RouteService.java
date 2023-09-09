package threekitties.tonghaeyo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import threekitties.tonghaeyo.domain.Organization;
import threekitties.tonghaeyo.domain.Route;
import threekitties.tonghaeyo.repository.RouteRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class RouteService {

    private final RouteRepository routeRepository;

    @Transactional(readOnly = true)
    public Route findById(Long id) {
        Optional<Route> opRoute = routeRepository.findById(id);

        if (opRoute.isEmpty()) {
            throw new IllegalArgumentException("Such a route doesn't exists.");
        }

        return opRoute.get();
    }

    public Route findByOrganization(Organization organization) {
        Optional<Route> opRoute = routeRepository.findByOrganization(organization);

        return opRoute.orElseGet(() -> routeRepository.save(new Route(organization)));
    }

    public Route save(Route route) {
        return routeRepository.save(route);
    }

}
