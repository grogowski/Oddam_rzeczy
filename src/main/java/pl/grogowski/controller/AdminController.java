package pl.grogowski.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import pl.grogowski.model.User;
import pl.grogowski.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String adminDashboard() {
        return "admin_dashboard";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String adminEdit() {
        return "edit_admin";
    }

    @RequestMapping(path = "/edit/password", method = RequestMethod.GET)
    public String adminEditPasswordForm() {
        return "admin_password";
    }

    @RequestMapping(path = "/edit/password", method = RequestMethod.POST)
    public String adminChangePassword(@SessionAttribute User user,  @RequestParam String oldPassword,
                                      @RequestParam String newPassword, @RequestParam String repeatPassword, Model model) {
        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            model.addAttribute("oldPasswordMessage", "Błędne hasło");
            return "admin_password";
        }
        if (!newPassword.equals(repeatPassword)) {
            model.addAttribute("passwordsMessage", "Hasło i powtórzone hasło muszą być być takie same");
            return "admin_password";
        }
        userService.updateUserPassword(user, newPassword);
        return "admin_dashboard";
    }

    @RequestMapping(path = "/edit/personal", method = RequestMethod.GET)
    public String adminEditPersonalForm(@SessionAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "admin_personal";
    }

    @RequestMapping(path = "/edit/personal", method = RequestMethod.POST)
    public String adminUpdatePersonalInfo(@SessionAttribute User user,  @RequestParam String firstName,
                                      @RequestParam String lastName,  @RequestParam String email, Model model) {
        if (!user.getEmail().equals(email)) {
            if (userService.userExists(email)) {
                model.addAttribute("emailMessage", "Użytkownik o adresie email "+email+" już istnieje");
                return "admin_personal";
            }
            user.setEmail(email);
        }
        if (!user.getFirstName().equals(firstName)) {
            user.setFirstName(firstName);
        }
        if (!user.getLastName().equals(lastName)) {
            user.setLastName(lastName);
        }
        userService.saveUser(user);
        return "admin_dashboard";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: /";
    }
}
