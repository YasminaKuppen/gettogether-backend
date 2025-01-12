package nl.novi.gettogetherbackend.repositories;

import nl.novi.gettogetherbackend.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findById(long id);
    List<Activity> findByTitle(String title);
List<Activity> findByAddedBy(String addedBy);
}
