package nl.novi.gettogetherbackend.repositories;

import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.models.Weekend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long id);
}
