package threekitties.tonghaeyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import threekitties.tonghaeyo.domain.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
