package threekitties.tonghaeyo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import threekitties.tonghaeyo.domain.Location;
import threekitties.tonghaeyo.repository.LocationRepository;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public Location save(Location location) {
        return locationRepository.save(location);
    }

}
