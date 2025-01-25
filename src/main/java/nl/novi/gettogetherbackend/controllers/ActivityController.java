package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.ActivityCreateDTO;
import nl.novi.gettogetherbackend.models.Activity;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.services.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.novi.gettogetherbackend.dtos.ActivityResponseDTO;
import nl.novi.gettogetherbackend.mappers.ActivityMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<?> createActivity(@Valid @RequestBody ActivityCreateDTO activityCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Activity activity = ActivityMapper.toEntity(activityCreateDTO);
        Activity savedActivity = activityService.save(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ActivityMapper.toResponseDTO(savedActivity));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> updateActivity(@PathVariable Long id, @RequestBody Activity activityDetails) {
        Optional<Activity> activityOptional = activityService.findById(id);
        if (activityOptional.isPresent()) {
            Activity activity = activityOptional.get();
            activity.setTitle(activityDetails.getTitle());
            activity.setDescription(activityDetails.getDescription());
            activity.setAddedBy(activityDetails.getAddedBy());
            Activity updatedActivity = activityService.save(activity);
            return ResponseEntity.ok(ActivityMapper.toResponseDTO(updatedActivity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        var result = activityService.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDTO>> getActivities(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) User addedBy
    ) {

        return ResponseEntity.ok(ActivityMapper.toResponseDTOList(activityService.getActivities(title, description, addedBy)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Activity>> findById(
            @PathVariable Long id) {

        Optional<Activity> responseDTO= activityService.findById(id);

        return ResponseEntity.ok(responseDTO);
    }
}