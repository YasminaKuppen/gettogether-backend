package nl.novi.gettogetherbackend.services;

import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.models.Weekend;
import nl.novi.gettogetherbackend.repositories.WeekendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeekendService {

    private final WeekendRepository weekendRepository;
    private final GroupService groupService;

    public WeekendService(WeekendRepository weekendRepository, GroupService groupService) {
        this.weekendRepository = weekendRepository;
        this.groupService = groupService;
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

    public List<Weekend> getUserWeekends(Long userId) {
        List<Group> groups = groupService.findByUser_id(userId);
        return groups.stream()
                .map(Group::getWeekend)
                .collect(Collectors.toList());
    }
}