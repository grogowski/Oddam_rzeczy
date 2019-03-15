package pl.grogowski.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.grogowski.model.Target;
import pl.grogowski.repository.TargetRepository;

public class TargetConverter implements Converter<String, Target> {
    @Autowired
    TargetRepository targetRepository;

    @Override
    public Target convert(String s) {
        if (s.isEmpty()) {
            return null;
        }
        return targetRepository.getOne(Long.parseLong(s));
    }
}
