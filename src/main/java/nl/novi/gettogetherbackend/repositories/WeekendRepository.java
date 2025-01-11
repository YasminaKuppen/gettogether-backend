package nl.novi.gettogetherbackend.repositories;

import nl.novi.gettogetherbackend.models.Weekend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WeekendRepository extends JpaRepository<Weekend, Long> {
    List<Weekend> findById(long id);
}
