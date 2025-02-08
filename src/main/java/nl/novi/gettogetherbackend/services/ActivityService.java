package nl.novi.gettogetherbackend.services;

import jakarta.transaction.Transactional;
import nl.novi.gettogetherbackend.models.*;

import nl.novi.gettogetherbackend.repositories.ActivityRepository;
import nl.novi.gettogetherbackend.repositories.ImageRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    public ActivityService(ActivityRepository activityRepository, ImageRepository imageRepository, ImageService imageService) {
        this.activityRepository = activityRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }
    public List<Activity> findAll() {return activityRepository.findAll();}
    public List<Activity> findAllByWeekendId(Long weekendId) {return activityRepository.findByWeekendId(weekendId);}

    public boolean delete(long id) {
        if (activityRepository.existsById(id)) {
           activityRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // add image
    @Transactional
    public Activity addImageToActivity(String filename, Long activityNumber) {
        Optional<Activity> optionalActivity = activityRepository.findById(activityNumber);
        Optional<Image> optionalPhoto = imageRepository.findByFileName(filename);

        if (optionalActivity.isPresent() && optionalPhoto.isPresent()) {
            Image image = optionalPhoto.get();
            Activity activity = optionalActivity.get();
            activity.setImage(image);
            return activityRepository.save(activity);
        } else {
            throw new RuntimeException("Activitiet of plaatje niet gevonden");
        }
    }

    @Transactional
    public Resource getImageFromActivity(Long id){
        Optional<Activity> optionalActivity = activityRepository.findById(id);
        if(optionalActivity.isEmpty()){
            throw new RuntimeException("Activiteit met " + id + " niet gevonden.");
        }
        Image image = optionalActivity.get().getImage();
        if(image == null){
            return imageService.loadImage("default.jpg");
        }
        return imageService.loadImage(image.getFileName());
    }
}