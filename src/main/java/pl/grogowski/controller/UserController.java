package pl.grogowski.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.grogowski.model.Donation;
import pl.grogowski.model.Organization;
import pl.grogowski.model.User;
import pl.grogowski.repository.CategoryRepository;
import pl.grogowski.repository.TargetRepository;
import pl.grogowski.service.CourierNoteService;
import pl.grogowski.service.DonationService;
import pl.grogowski.service.OrganizationService;
import pl.grogowski.service.UserService;
import pl.grogowski.util.UtilityClass;

import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    CourierNoteService courierNoteService;

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
    public String collectDataForm1(@RequestParam (required = false) List<Long> categories, HttpSession session, Model model) {
        if (categories==null) {
            model.addAttribute("selectMessage", "Zaznacz minimum jedną kategorię darów, które chcesz przekazać na cele dobroczynne.");
            model.addAttribute("categories", categoryRepository.findAll());
            return "form1";
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
    public String collectDataForm3a( @RequestParam Long location, @RequestParam Long target, @RequestParam String name, HttpSession session) {
        if (!name.isEmpty()) {
            List<Organization> matchedByName = organizationService.getMatchingActiveOrganizationsByName(name);
            if (matchedByName!=null) {
                session.setAttribute("organizations", matchedByName);
                return "form3b";
            }
        }
        if (location!=0&&target!=0) {
            List<Organization> matched = organizationService.getMatchingOrganizationsByLocationAndTarget(location, target);
            if (!matched.isEmpty()) {
                session.setAttribute("organizations", matched);
                return "form3b";
            }
        }
        if (location!=0) {
            List<Organization> matched = organizationService.getMatchingOrganizationsByLocation(location);
            if (!matched.isEmpty()) {
                session.setAttribute("organizations", matched);
                return "form3b";
            }
        }
        if (target!=0) {
            List<Organization> matched = organizationService.getMatchingOrganizationsByTarget(target);
            if (!matched.isEmpty()) {
                session.setAttribute("organizations", matched);
                return "form3b";
            }
        }
        session.setAttribute("organizations", organizationService.getActiveOrganizations());
        return "form3b";
    }

    @RequestMapping(path = "/form3b", method = RequestMethod.POST)
    public String collectDataForm3b(@RequestParam (required = false) Long organization, HttpSession session, Model model) {
        if (organization==null) {
            model.addAttribute("organizationMessage", "Wybierz organizację, której chcesz przekazać dary");
            return "form3b";
        }
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
        if (LocalDate.parse(date).minusDays(2).isBefore(LocalDate.now())||LocalDate.parse(date).getDayOfWeek().equals(DayOfWeek.SUNDAY)||LocalDate.parse(date).getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            model.addAttribute("dateMessage", "Data odebrania musi być dniem roboczym, minimum 2 dni od daty bieżącej");
            return  "form4";
        }
        if (!time.trim().matches("((0)?[8-9]|1[0-7]):[0-5][0-9]|18:00")) {
            model.addAttribute("timeMessage", "Czas musi być w formacie 24h gg:mm, w przedziale 08:00-17:59");
            return "form4";
        }
        session.setAttribute("address", UtilityClass.mergeAddress(street, city, zip, phone));
        session.setAttribute("datetime", UtilityClass.mergeDateTime(time, date));
        session.setAttribute("remarks", remarks);
        session.setAttribute("collected", LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time)));
        List<String> donatedCategories = new ArrayList<>();
        for (Long id:categories) {
            donatedCategories.add(categoryRepository.findOne(id).getName());
        }
        model.addAttribute("donatedCategories", donatedCategories);
        model.addAttribute("donated", UtilityClass.getTextBasedOnNumberOfBags(bags));
        return "donation_summary";
    }

    @RequestMapping(path = "/donate", method = RequestMethod.GET)
    public String saveDonation(@SessionAttribute User user, @SessionAttribute List<Long> categories,
                               @SessionAttribute Integer bags, @SessionAttribute Organization organization,
                               @SessionAttribute LocalDateTime collected, @SessionAttribute String address,
                               @SessionAttribute String remarks, HttpSession session) {
        donationService.saveNewDonation(user, categories, bags, organization, LocalDateTime.now(), collected);
        courierNoteService.saveNewCourierNote(organization.getAddress(), address, collected, remarks);
        session.removeAttribute("categories");
        session.removeAttribute("bags");
        session.removeAttribute("organization");
        session.removeAttribute("address");
        session.removeAttribute("datetime");
        session.removeAttribute("remarks");
        session.removeAttribute("collected");
        return "redirect: /user/main";
    }

    @RequestMapping(path = "/collect/{donationId}")
    public String archiveDonation(@PathVariable Long donationId) {
        Donation toBeArchived = donationService.getDonation(donationId);
        toBeArchived.setWasCollected(true);
        donationService.saveDonation(toBeArchived);
        return "redirect: /user/donations";
    }

    @RequestMapping(path = "/donations", method = RequestMethod.GET)
    public String showUserDonations(@SessionAttribute User user, Model model) {
        model.addAttribute("donations", donationService.getUserDonations(user));
        return "donations";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: /";
    }

}
