package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grogowski.model.Target;

public interface TargetRepository extends JpaRepository<Target, Long> {

}
