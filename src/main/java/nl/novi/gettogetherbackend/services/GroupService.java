package nl.novi.gettogetherbackend.services;

import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    public boolean delete(long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;

        } else {
            return false;
        }
    }

    public List<Group> getGroups(
            String type,
            User creator
    ) {
        List<Group> groups;
        groups = groupRepository.findAll();
        return groups;
    }
}