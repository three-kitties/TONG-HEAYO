package threekitties.tonghaeyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import threekitties.tonghaeyo.domain.Organization;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByName(String name);

}
