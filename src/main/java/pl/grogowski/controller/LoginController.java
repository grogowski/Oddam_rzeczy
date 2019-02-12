package pl.grogowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.grogowski.model.User;
import pl.grogowski.service.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginAction() {
        return "zalogowano";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String registerAction(@Valid User user, BindingResult result, Model model, @RequestParam String repeatPassword) {
        if (userService.userExists(user)) {
            model.addAttribute("emailMessage", "Użytkownik o tym adresie email już istnieje");
            return "register";
        }
        if (!result.hasErrors()) {
            if (!user.getPassword().equals(repeatPassword)) {
                model.addAttribute("passwordsMessage", "Hasło i powtórzone hasło muszą być być takie same");
                return "register";
            }
            userService.registerUser(user);
            return "index";
        }
        return "register";
    }
}
