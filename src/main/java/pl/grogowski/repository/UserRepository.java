package pl.grogowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.grogowski.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    long countByEmail(String email);

    User findByEmail(String email);

    List<User> findAllByAdmin(Boolean admin);

    @Query(value = "SELECT * FROM users WHERE admin = true AND id NOT IN (SELECT id FROM users WHERE id = ?1)",  nativeQuery = true)
    List<User> findAllAdminsExceptUser(Long userId);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User customGetById(Long id);

}
