package nl.novi.gettogetherbackend.repositories;

import nl.novi.gettogetherbackend.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findById(long id);
    List<Group> findByUsers_Id(Long userId);
}
