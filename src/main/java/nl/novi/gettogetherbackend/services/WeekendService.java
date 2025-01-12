package nl.novi.gettogetherbackend.services;

import nl.novi.gettogetherbackend.models.Weekend;
import nl.novi.gettogetherbackend.repositories.WeekendRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WeekendService {
    private final WeekendRepository weekendRepository;

    public WeekendService(WeekendRepository weekendRepository) {
        this.weekendRepository = weekendRepository;
    }

    public Weekend save(Weekend weekend) {
        return weekendRepository.save(weekend);
    }

    public Optional<Weekend> findById(Long id) {
        return weekendRepository.findById(id);
    }

    public boolean delete(long id) {
        if (weekendRepository.existsById(id)) {
            weekendRepository.deleteById(id);
            return true;

        } else {
            return false;
        }
    }

    public List<Weekend> getWeekends(
            Date date,
            String time,
            String location,
            int temperature
    ) {
        List<Weekend> weekends;
        weekends = weekendRepository.findAll();
        return weekends;
    }
}