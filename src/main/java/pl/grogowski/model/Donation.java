package pl.grogowski.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
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

    public String getStringCreated() {
        return created.toLocalDate().toString();
    }

    public String getStringCollected() {
        return collected.toLocalDate().toString();
    }
}
