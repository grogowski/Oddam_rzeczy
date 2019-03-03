package pl.grogowski.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import pl.grogowski.model.Organization;
import pl.grogowski.model.User;
import pl.grogowski.repository.CategoryRepository;
import pl.grogowski.repository.TargetRepository;
import pl.grogowski.service.DonationService;
import pl.grogowski.service.OrganizationService;
import pl.grogowski.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DonationService donationService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TargetRepository targetRepository;

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String adminDashboard(@SessionAttribute User user, Model model) {
        model.addAttribute("bagsTotal", donationService.getTotalNumberOfDonatedBags(user));
        model.addAttribute("organizationsTotal", donationService.getSupportedOrganizationsNumber(user));
        model.addAttribute("collectionsTotal", 0);
        return "user_dashboard";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String userEdit() {
        return "edit_user";
    }

    @RequestMapping(path = "/edit/password", method = RequestMethod.GET)
    public String adminEditPasswordForm() {
        return "user_password";
    }

    @RequestMapping(path = "/edit/password", method = RequestMethod.POST)
    public String adminChangePassword(@SessionAttribute User user, @RequestParam String oldPassword,
                                      @RequestParam String newPassword, @RequestParam String repeatPassword, Model model) {
        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            model.addAttribute("oldPasswordMessage", "Błędne hasło");
            return "user_password";
        }
        if (!newPassword.equals(repeatPassword)) {
            model.addAttribute("passwordsMessage", "Hasło i powtórzone hasło muszą być być takie same");
            return "user_password";
        }
        userService.updateUserPassword(user, newPassword);
        return "redirect: /user/main";
    }

    @RequestMapping(path = "/edit/personal", method = RequestMethod.GET)
    public String adminEditPersonalForm(@SessionAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "user_personal";
    }

    @RequestMapping(path = "/edit/personal", method = RequestMethod.POST)
    public String adminUpdatePersonalInfo(@SessionAttribute User user, @RequestParam String firstName,
                                          @RequestParam String lastName, @RequestParam String email, Model model) {
        if (!user.getEmail().equals(email)) {
            if (userService.userExists(email)) {
                model.addAttribute("emailMessage", "Użytkownik o adresie email " + email + " już istnieje");
                model.addAttribute("user", user);
                return "user_personal";
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
        return "redirect: /user/main";
    }


    @RequestMapping(path = "/form1", method = RequestMethod.GET)
    public String showForm1(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "form1";
    }

    @RequestMapping(path = "/form1", method = RequestMethod.POST)
    public String collectDataForm1(@RequestParam (required = false) List<Long> categories, HttpSession session) {
        if (categories==null) {
            return "redirect: /user/form1";
        }
        session.setAttribute("categories", categories);
        return "form2";
    }

    @RequestMapping(path = "/form2", method = RequestMethod.GET)
    public String showForm2() {
        return "form2";
    }

    @RequestMapping(path = "/form2", method = RequestMethod.POST)
    public String collectDataForm2(@RequestParam Integer bags, HttpSession session) {
        session.setAttribute("bags", bags);
        return "redirect: /user/form3a";
    }

    @RequestMapping(path = "/form3a", method = RequestMethod.GET)
    public String showForm3a(Model model) {
        model.addAttribute("locations", organizationService.getLocationsOfActiveOrganizations());
        model.addAttribute("targets", targetRepository.findAll());
        return "form3a";
    }

    @RequestMapping(path = "/form3a", method = RequestMethod.POST)
    public String collectDataForm3a( @RequestParam Long location, @RequestParam Long target, @RequestParam String name, Model model) {
        if (location == 0 && target == 0 && name == null) {
            return "redirect: /user/form3a";
        }
        if (!name.isEmpty()) {
            List<Organization> matchedByName = organizationService.getMatchingOrganizationsByName(name);
            if (matchedByName!=null) {
                model.addAttribute("organizations", matchedByName);
                return "form3b";
            }
        }
        if (location!=0&&target!=0) {
            List<Organization> matched = organizationService.getMatchingOrganizationsByLocationAndTarget(location, target);
            if (!matched.isEmpty()) {
                model.addAttribute("organizations", matched);
                return "form3b";
            }
        }
        if (location!=0) {
            List<Organization> matched = organizationService.getMatchingOrganizationsByLocation(location);
            if (!matched.isEmpty()) {
                model.addAttribute("organizations", matched);
                return "form3b";
            }
        }
        if (target!=0) {
            List<Organization> matched = organizationService.getMatchingOrganizationsByTarget(target);
            if (!matched.isEmpty()) {
                model.addAttribute("organizations", matched);
                return "form3b";
            }
        }
        model.addAttribute("organizations", organizationService.getOrganizations());
        return "form3b";
    }

    @RequestMapping(path = "/form3b", method = RequestMethod.POST)
    public String collectDataForm3b(@RequestParam Long organization, HttpSession session) {
        session.setAttribute("organization", organizationService.getOrganizationById(organization));
        return "redirect: /user/form4";
    }

    @RequestMapping(path = "/form4", method = RequestMethod.GET)
    public String showForm4() {
        return "form4";
    }

    @RequestMapping(path = "/form4", method = RequestMethod.POST)
    public String collectDataForm4(@SessionAttribute Integer bags, @SessionAttribute List<Long> categories,
                                   Model model, HttpSession session,
                                   @RequestParam String street, @RequestParam String city,
                                   @RequestParam String zip, @RequestParam String phone,
                                   @RequestParam String remarks, @RequestParam String date,
                                   @RequestParam String time) {
        String address = street.trim()+", "+zip.trim()+" "+city.trim() + " tel. "+phone.trim();
        session.setAttribute("address", address);
        String datetime = time.trim() + " " + date.trim();
        session.setAttribute("datetime", datetime);
        session.setAttribute("remarks", remarks);
        String donatedStuff;
        if (bags == 1) {
            donatedStuff = bags+ " worek:";
        } else if (bags<5) {
            donatedStuff = bags + " worki:";
        } else {
            donatedStuff = bags + " worków:";
        }
        List<String> donatedCategories = new ArrayList<>();
        for (Long id:categories) {
            donatedCategories.add(categoryRepository.findOne(id).getName());
        }
        model.addAttribute("donatedCategories", donatedCategories);
        model.addAttribute("donated", donatedStuff);
        return "donation_summary";
    }

    @RequestMapping(path = "/donate", method = RequestMethod.GET)
    public String saveDonation(@SessionAttribute User user, @SessionAttribute List<Long> categories,
                               @SessionAttribute Integer bags, @SessionAttribute Organization organization, HttpSession session) {
        donationService.saveNewDonation(user, categories, bags, organization, LocalDateTime.now());
        session.removeAttribute("categories");
        session.removeAttribute("bags");
        session.removeAttribute("organization");
        session.removeAttribute("address");
        session.removeAttribute("datetime");
        session.removeAttribute("remarks");
        return "redirect: /user/main";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: /";
    }

}
