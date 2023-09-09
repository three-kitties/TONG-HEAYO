package threekitties.tonghaeyo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import threekitties.tonghaeyo.domain.Organization;
import threekitties.tonghaeyo.domain.Route;
import threekitties.tonghaeyo.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization findById(Long id) {
        Optional<Organization> opOrganization = organizationRepository.findById(id);
        validateOrganizationExists(opOrganization);
        return opOrganization.get();
    }

    public Organization findByName(String name) {
        Optional<Organization> opOrganization = organizationRepository.findByName(name);
        validateOrganizationExists(opOrganization);
        return opOrganization.get();
    }

    private void validateOrganizationExists(Optional<Organization> opOrganization) {
        if (opOrganization.isEmpty()) {
            throw new IllegalArgumentException("Such an organization doesn't exists.");
        }
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Transactional
    public void saveRoute(Organization organization, Route route) {
        organizationRepository.save(organization.toBuilder().route(route).build());
    }

}
