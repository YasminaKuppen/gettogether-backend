package nl.novi.gettogetherbackend.services;

import nl.novi.gettogetherbackend.models.Activity;

import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

    public boolean delete(long id) {
        if (activityRepository.existsById(id)) {
           activityRepository.deleteById(id);
            return true;

        } else {
            return false;
        }
    }

    public List<Activity> getActivities(
            String title,
            String description,
            User addedBy
    ) {
        List<Activity> activities;
        if (title != null) {
            activities = activityRepository.findByTitle(title);
        } else if (addedBy != null) {
            activities = activityRepository.findByAddedBy(addedBy);
        } else {
            activities = activityRepository.findAll();
        }
        return activities;
    }
}