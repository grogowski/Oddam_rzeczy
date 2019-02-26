package pl.grogowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String adminEditPassword() {
        return "admin_password";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: /";
    }
}
