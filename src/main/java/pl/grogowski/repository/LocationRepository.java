package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grogowski.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
