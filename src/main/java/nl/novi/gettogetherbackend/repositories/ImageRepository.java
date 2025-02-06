package nl.novi.gettogetherbackend.repositories;

import nl.novi.gettogetherbackend.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Optional<Image> findByFileName(String fileName);
}
