package pl.grogowski.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.grogowski.model.Organization;
import pl.grogowski.model.User;
import pl.grogowski.repository.LocationRepository;
import pl.grogowski.repository.TargetRepository;
import pl.grogowski.service.DonationService;
import pl.grogowski.service.OrganizationService;
import pl.grogowski.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validator;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    DonationService donationService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    TargetRepository targetRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    Validator validator;

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String adminDashboard() {
        return "admin_dashboard";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String adminEdit() {
        return "admin_edit";
    }

    @RequestMapping(path = "/edit/password", method = RequestMethod.GET)
    public String adminEditPasswordForm() {
        return "admin_password";
    }

    @RequestMapping(path = "/edit/password", method = RequestMethod.POST)
    public String adminChangePassword(@SessionAttribute User user, @RequestParam String oldPassword,
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
    public String adminUpdatePersonalInfo(@SessionAttribute User user, @RequestParam String firstName,
                                          @RequestParam String lastName, @RequestParam String email, Model model) {
        if (!user.getEmail().equals(email)) {
            if (userService.userExists(email)) {
                model.addAttribute("emailMessage", "Użytkownik o adresie email " + email + " już istnieje");
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

    @RequestMapping(path = "/admins", method = RequestMethod.GET)
    public String adminsPage(@SessionAttribute User user, Model model) {
        model.addAttribute("admins", userService.getAdmins(user));
        return "admin_admins";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteAdmin(@PathVariable Long id) {
        boolean isAdmin = userService.userIdAdmin(id);
        donationService.setUserToNullInAllDonations(id);
        userService.deleteUser(id);
        if (isAdmin) {
            return "redirect: /admin/admins";
        }
        return "redirect: /admin/users";
    }

    @RequestMapping(path = "/take_admin_privileges/{id}", method = RequestMethod.GET)
    public String takeAdmin(@PathVariable Long id) {
        userService.takeAdmin(id);
        return "redirect: /admin/admins";
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String usersPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin_users";
    }

    @RequestMapping(path = "/organizations", method = RequestMethod.GET)
    public String organizationsPage(Model model) {
        model.addAttribute("organizations", organizationService.getOrganizations() );
        return "admin_organizations";
    }

    @RequestMapping(path = "/deactivate_organization/{id}", method = RequestMethod.GET)
    public String deactivateOrganization(@PathVariable Long id) {
        organizationService.changeOrganizationStatus(id, false);
        return "redirect: /admin/organizations";
    }

    @RequestMapping(path = "/activate_organization/{id}", method = RequestMethod.GET)
    public String activateOrganization(@PathVariable Long id) {
        organizationService.changeOrganizationStatus(id, true);
        return "redirect: /admin/organizations";
    }

    @RequestMapping(path = "/new_organization", method = RequestMethod.GET)
    public String createOrganization(Model model) {
        model.addAttribute("organization", new Organization());
        model.addAttribute("targets", targetRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        return "admin_new_organization";
    }

    @RequestMapping(path = "/new_organization", method = RequestMethod.POST)
    public String saveOrganization(@Valid Organization organization, BindingResult result) {
        if (result.hasErrors()) {
            return "admin_new_organization";
        }
        organizationService.saveOrganization(organization);
        return "redirect: /admin/organizations";
    }

    @RequestMapping(path = "/edit_organization/{id}", method = RequestMethod.GET)
    public String editOrganization(@PathVariable Long id, Model model) {
        model.addAttribute("organization", organizationService.getOrganizationById(id));
        model.addAttribute("targets", targetRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        return "admin_edit_organization";
    }

    @RequestMapping(path = "/edit_organization/{id}", method = RequestMethod.POST)
    public String saveEditedOrganization(@Valid Organization organization, BindingResult result) {
        if (result.hasErrors()) {
            return "admin_edit_organization";
        }
        organizationService.saveOrganization(organization);
        return "redirect: /admin/organizations";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: /";
    }
}
