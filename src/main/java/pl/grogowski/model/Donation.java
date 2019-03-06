package pl.grogowski.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Organization organization;

    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;

    @Min(1)
    @Max(10)
    private Integer bags;

    private LocalDateTime created;

    private LocalDateTime collected;

    private Boolean wasCollected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getBags() {
        return bags;
    }

    public void setBags(Integer bags) {
        this.bags = bags;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime date) {
        this.created = date;
    }

    public LocalDateTime getCollected() {
        return collected;
    }

    public void setCollected(LocalDateTime collected) {
        this.collected = collected;
    }

    public Boolean getWasCollected() {
        return wasCollected;
    }

    public void setWasCollected(Boolean wasCollected) {
        this.wasCollected = wasCollected;
    }

    public String getStringCreated() {
        return created.toLocalDate().toString();
    }

    public String getStringCollected() {
        return collected.toLocalDate().toString();
    }
}
