package pl.grogowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grogowski.model.Location;
import pl.grogowski.model.Organization;
import pl.grogowski.repository.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public List<Location> getLocationsOfActiveOrganizations() {
        return new ArrayList<>(organizationRepository.queryGetLocationsOfActiveOrganizations());
    }

    public List<Organization> getMatchingActiveOrganizationsByName(String name) {
        List<Organization> all = organizationRepository.findAllByActive(true);
        List<Organization> matching = new ArrayList<>();
        name = name.trim().toLowerCase();
        for (Organization o : all) {
            if (name.equals(o.getName().toLowerCase()) || o.getName().toLowerCase().contains(name)) {
                matching.add(o);
            }
        }
        if (matching.isEmpty()) {
            return null;
        } else {
            return matching;
        }
    }

    public List<Organization> getMatchingOrganizationsByLocationAndTarget(Long locationId, Long targetId) {
        return organizationRepository.findAllByLocation_IdAndTarget_IdAndActive(locationId, targetId, true);
    }

    public List<Organization> getMatchingOrganizationsByLocation(Long locationId) {
        return organizationRepository.findAllByLocation_IdAndActive(locationId, true);
    }

    public List<Organization> getMatchingOrganizationsByTarget(Long targetId) {
        return organizationRepository.findAllByTarget_IdAndActive(targetId, true);
    }

    public Object getOrganizations() {
        return organizationRepository.findAll();
    }

    public List<Organization> getActiveOrganizations() {
        return organizationRepository.findAllByActive(true);
    }

    public Organization getOrganizationById(Long id) {
        return organizationRepository.findOne(id);
    }

    public void changeOrganizationStatus(Long id, boolean newStatus) {
        Organization organization = organizationRepository.findOne(id);
        organization.setActive(newStatus);
        organizationRepository.save(organization);

    }

    public void saveOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

}
