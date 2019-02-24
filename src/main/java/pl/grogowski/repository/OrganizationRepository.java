package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.grogowski.model.Location;
import pl.grogowski.model.Organization;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllByLocation_IdAndTarget_Id(Long locationId, Long targetId);

    List<Organization> findAllByLocation_Id(Long locationId);

    List<Organization> findAllByTarget_Id(Long targetId);

    @Query("select distinct o.location from Organization o where o.active = true")
    List<Location> queryGetLocationsOfActiveOrganizations();

}
