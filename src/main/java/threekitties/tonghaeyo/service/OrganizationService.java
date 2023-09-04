package threekitties.tonghaeyo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import threekitties.tonghaeyo.domain.Organization;
import threekitties.tonghaeyo.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Optional<Organization> findById(Long id) {
        return organizationRepository.findById(id);
    }

    public Optional<Organization> findByName(String name) {
        return organizationRepository.findByName(name);
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

}
