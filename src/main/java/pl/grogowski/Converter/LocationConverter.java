package pl.grogowski.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.grogowski.model.Location;
import pl.grogowski.repository.LocationRepository;

public class LocationConverter implements Converter<String, Location> {
    @Autowired
    LocationRepository locationRepository;

    @Override
    public Location convert(String s) {
        if (s.isEmpty()) {
            return null;
        }
        return locationRepository.findOne(Long.parseLong(s));
    }
}
