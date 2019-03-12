package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grogowski.model.CourierNote;

public interface CourierNoteRepository extends JpaRepository<CourierNote, Long> {

}
