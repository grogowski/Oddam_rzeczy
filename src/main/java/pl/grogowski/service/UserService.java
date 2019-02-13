package pl.grogowski.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grogowski.model.User;
import pl.grogowski.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void registerUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }

    public boolean userExists(User user) {
        return userRepository.countByEmail(user.getEmail())==1;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user!=null&&BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

}
