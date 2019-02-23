package pl.grogowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import pl.grogowski.model.User;
import pl.grogowski.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String adminDashboard(@SessionAttribute User user, Model model) {
        model.addAttribute("userName", user.getFirstName());
        return "user_dashboard";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: /";
    }
}
