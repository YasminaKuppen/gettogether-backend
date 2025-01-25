package nl.novi.gettogetherbackend.repositories;

import nl.novi.gettogetherbackend.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findById(long id);
}
