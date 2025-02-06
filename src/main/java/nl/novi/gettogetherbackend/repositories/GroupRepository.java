package nl.novi.gettogetherbackend.repositories;

import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findById(long id);
}
