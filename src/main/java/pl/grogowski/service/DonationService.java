package pl.grogowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grogowski.model.Category;
import pl.grogowski.model.Donation;
import pl.grogowski.model.Organization;
import pl.grogowski.model.User;
import pl.grogowski.repository.CategoryRepository;
import pl.grogowski.repository.DonationRepository;
import pl.grogowski.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Integer getSupportedOrganizationsNumber(User user) {
        return donationRepository.queryCountDistinctOrganizationsByUser(user);
    }

    public Integer getTotalNumberOfDonatedBags(User user) {
        List<Donation> userDonations = donationRepository.findAllByUser(user);
        Integer totalBags = 0;
        for (Donation d:userDonations) {
            totalBags+=d.getBags();
        }
        return totalBags;
    }

    public void saveNewDonation(User user, List<Long> categories, Integer bags, Organization organization, LocalDateTime date) {
        Donation newDonation = new Donation();
        newDonation.setUser(user);
        newDonation.setBags(bags);
        List<Category> categoryList = new ArrayList<>();
        for (Long l:categories) {
            categoryList.add(categoryRepository.findOne(l));
        }
        newDonation.setCategories(categoryList);
        newDonation.setOrganization(organization);
        newDonation.setDate(date);
        donationRepository.save(newDonation);
    }

    public void setUserToNullInAllDonations(Long id) {
        List<Donation> userDonations = donationRepository.findAllByUserId(id);
        for (Donation d: userDonations) {
            d.setUser(null);
            donationRepository.save(d);
        }
    }
}
