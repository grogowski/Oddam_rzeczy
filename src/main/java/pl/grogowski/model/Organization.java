package pl.grogowski.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table (name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=10, max=250)
    private String name;

    @Size(min=10, max=250)
    private String address;

    @NotNull
    @ManyToOne
    private Location location;

    @NotNull
    @ManyToOne
    private Target target;

    private Boolean active;

}
