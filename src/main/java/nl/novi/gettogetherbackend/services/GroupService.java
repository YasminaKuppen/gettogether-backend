package nl.novi.gettogetherbackend.services;

import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.repositories.GroupRepository;
import org.springframework.stereotype.Service;

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
    public List<Group> findByUser_id(Long id) {return groupRepository.findByUsers_Id(id);}

    public boolean delete(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Group> getGroups() {
        List<Group> groups;
        groups = groupRepository.findAll();
        return groups;
    }

}