package pl.grogowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grogowski.model.User;
import pl.grogowski.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean userExists(User user) {
        return userRepository.countByEmail(user.getEmail())==1;
    }

}
