package pl.grogowski.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grogowski.model.User;
import pl.grogowski.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void registerUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }

    public void updateUserPassword(User user, String newPassword) {
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        return userRepository.countByEmail(email)==1;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user!=null&&BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAdmins(User user) {
        return userRepository.findAllAdminsExceptUser(user.getId());
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public void takeAdmin(Long id) {
        User user = userRepository.findOne(id);
        user.setAdmin(false);
        userRepository.save(user);
    }
}
