package pl.grogowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grogowski.model.CourierNote;
import pl.grogowski.repository.CourierNoteRepository;

import java.time.LocalDateTime;

@Service
public class CourierNoteService {

    @Autowired
    CourierNoteRepository courierNoteRepository;


    public void saveNewCourierNote(String deliveryAddress, String collectionAddress, LocalDateTime collectionDateTime, String remarks) {
        CourierNote newCourierNote = new CourierNote();
        newCourierNote.setDeliveryAddress(deliveryAddress);
        newCourierNote.setCollectionAddress(collectionAddress);
        newCourierNote.setCollectionDateTime(collectionDateTime);
        newCourierNote.setRemarks(remarks);
        courierNoteRepository.save(newCourierNote);
    }
}
