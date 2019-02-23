package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grogowski.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

}
