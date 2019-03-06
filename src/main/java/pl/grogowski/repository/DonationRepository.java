package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.grogowski.model.Donation;
import pl.grogowski.model.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findAllByUserAndWasCollected(User user, Boolean wasCollected);

    List<Donation> findAllByUserOrderByWasCollectedAsc(User user);

    List<Donation> findAllByUserId(Long userId);

    @Query("select count (distinct d.organization) from Donation d where d.user = ?1 and d.wasCollected=true")
    Integer queryCountDistinctOrganizationsByUser(User user);

}
