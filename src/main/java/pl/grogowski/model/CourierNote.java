package pl.grogowski.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollectionAddress() {
        return collectionAddress;
    }

    public void setCollectionAddress(String collectionAddress) {
        this.collectionAddress = collectionAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getCollectionDateTime() {
        return collectionDateTime;
    }

    public void setCollectionDateTime(LocalDateTime collectionDateTime) {
        this.collectionDateTime = collectionDateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
