package pl.grogowski.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "courier_notes")
public class CourierNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String collectionAddress;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private LocalDateTime collectionDateTime;

    private String remarks;

}
