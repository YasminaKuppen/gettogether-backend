package nl.novi.gettogetherbackend.services;

import nl.novi.gettogetherbackend.models.Image;
import nl.novi.gettogetherbackend.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

// Checked

@Service
public class ImageService {
    private final Path fileStoragePath;
    private final String fileStorageLocation;
    private final ImageRepository imageRepository;

    public ImageService(@Value("activityImages") String fileStorageLocation, ImageRepository imageRepository) throws IOException {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;
        this.imageRepository = imageRepository;

        Files.createDirectories(fileStoragePath);
    }

    public String storeImage(MultipartFile file, Long activityId) throws IOException {
        String fileName = activityId + "_" + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "/" + fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        imageRepository.save(new Image(fileName));
        return fileName;
    }

    public Resource loadImage(String fileName) {
        Path filePath = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);
        Resource resource;

        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Kan bestand niet lezen", e);
        }

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Bestand bestaat niet of fout in bestand.");
        }
    }
}
