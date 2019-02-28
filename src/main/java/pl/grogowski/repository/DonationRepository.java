package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.grogowski.model.Donation;
import pl.grogowski.model.User;

import java.time.LocalDate;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findAllByUser(User user);

    List<Donation> findAllByUserId(Long userId);

    @Query("select count (distinct d.organization) from Donation d where d.user = ?1")
    Integer queryCountDistinctOrganizationsByUser(User user);

}
