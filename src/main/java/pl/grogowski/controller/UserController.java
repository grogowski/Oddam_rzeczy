package pl.grogowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("userName", user.getFirstName());
        model.addAttribute("bagsTotal", donationService.getTotalNumberOfDonatedBags(user));
        model.addAttribute("organizationsTotal", donationService.getSupportedOrganizationsNumber(user));
        model.addAttribute("collectionsTotal", 0);
        return "user_dashboard";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: /";
    }

    @RequestMapping(path = "/form1", method = RequestMethod.GET)
    public String showForm1(@SessionAttribute User user, Model model) {
        model.addAttribute("userName", user.getFirstName());
        model.addAttribute("categories", categoryRepository.findAll());
        return "form1";
    }

    @RequestMapping(path = "/form1", method = RequestMethod.POST)
    public String collectDataForm1(@SessionAttribute User user, @RequestParam (required = false) List<Long> categories, Model model, HttpSession session) {
        if (categories==null) {
            return "redirect: /user/form1";
        }
        model.addAttribute("userName", user.getFirstName());
        session.setAttribute("categories", categories);
        return "form2";
    }

    @RequestMapping(path = "/form2", method = RequestMethod.GET)
    public String showForm2(@SessionAttribute User user, Model model) {
        model.addAttribute("userName", user.getFirstName());
        return "form2";
    }

    @RequestMapping(path = "/form2", method = RequestMethod.POST)
    public String collectDataForm2(@SessionAttribute User user, @RequestParam Integer bags, Model model, HttpSession session) {
        session.setAttribute("bags", bags);
        model.addAttribute("userName", user.getFirstName());
        model.addAttribute("locations", organizationService.getLocationsOfActiveOrganizations());
        model.addAttribute("targets", targetRepository.findAll());
        return "form3a";
    }

    @RequestMapping(path = "/form3a", method = RequestMethod.GET)
    public String showForm3a(@SessionAttribute User user, Model model) {
        model.addAttribute("userName", user.getFirstName());
        model.addAttribute("locations", organizationService.getLocationsOfActiveOrganizations());
        model.addAttribute("targets", targetRepository.findAll());
        return "form3a";
    }

    @RequestMapping(path = "/form3a", method = RequestMethod.POST)
    public String collectDataForm3a(@SessionAttribute User user, @RequestParam Long location, @RequestParam Long target, @RequestParam(required = false) String name, Model model) {
        model.addAttribute("userName", user.getFirstName());
        if (location == 0 && target == 0 && name == null) {
            return "redirect: /user/form3a";
        }
        if (name!=null) {
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
            }
        }
        if (location!=0) {
            List<Organization> matched = organizationService.getMatchingOrganizationsByLocation(location);
            if (!matched.isEmpty()) {
                model.addAttribute("organizations", matched);
            }
        }
        if (target!=0) {
            List<Organization> matched = organizationService.getMatchingOrganizationsByTarget(target);
            if (!matched.isEmpty()) {
                model.addAttribute("organizations", matched);
            }
        }
        model.addAttribute("organizations", organizationService.getSomeOrganizations());
        return "form3b";
    }

    @RequestMapping(path = "/form3b", method = RequestMethod.POST)
    public String collectDataForm3b(@SessionAttribute User user, @RequestParam Long organization, Model model, HttpSession session) {
        session.setAttribute("organization", organizationService.getOrganizationById(organization));
        model.addAttribute("userName", user.getFirstName());
        return "form4";
    }

    @RequestMapping(path = "/form4", method = RequestMethod.GET)
    public String showForm4(@SessionAttribute User user, Model model) {
        model.addAttribute("userName", user.getFirstName());
        return "form4";
    }

    @RequestMapping(path = "/form4", method = RequestMethod.POST)
    public String collectDataForm4(@SessionAttribute User user,
                                   @SessionAttribute List<Long> categories,
                                   @SessionAttribute Integer bags,
                                   @SessionAttribute Organization organization,
                                   @RequestParam String street,
                                   @RequestParam String city,
                                   @RequestParam String zip,
                                   @RequestParam String phone,
                                   @RequestParam String remarks,
                                   @RequestParam String date,
                                   @RequestParam String time) {
        donationService.saveNewDonation(user, categories, bags, organization, LocalDateTime.now());
        return "redirect: /user/main";
    }

}
