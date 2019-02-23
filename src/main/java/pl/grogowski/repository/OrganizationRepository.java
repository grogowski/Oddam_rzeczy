package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grogowski.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
