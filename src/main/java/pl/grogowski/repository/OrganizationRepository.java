package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.grogowski.model.Location;
import pl.grogowski.model.Organization;

import java.util.List;
import java.util.TreeSet;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllByLocation_IdAndTarget_IdAndActive(Long locationId, Long targetId, Boolean active);

    List<Organization> findAllByLocation_IdAndActive(Long locationId, Boolean active);

    List<Organization> findAllByTarget_IdAndActive(Long targetId, Boolean active);

    @Query("select distinct o.location from Organization o where o.active = true")
    TreeSet<Location> queryGetLocationsOfActiveOrganizations();

}
