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
        return organizationRepository.queryGetLocationsOfActiveOrganizations();
    }

    public List<Organization> getMatchingOrganizationsByName(String name) {
        List<Organization> all = organizationRepository.findAll();
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
        return organizationRepository.findAllByLocation_IdAndTarget_Id(locationId, targetId);
    }

    public List<Organization> getMatchingOrganizationsByLocation(Long locationId) {
        return organizationRepository.findAllByLocation_Id(locationId);
    }

    public List<Organization> getMatchingOrganizationsByTarget(Long targetId) {
        return organizationRepository.findAllByTarget_Id(targetId);
    }

    public List<Organization> getSomeOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization getOrganizationById(Long id) {
        return organizationRepository.findOne(id);
    }
}
