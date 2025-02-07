package nl.novi.gettogetherbackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.ActivityCreateDTO;
import nl.novi.gettogetherbackend.models.Activity;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.models.Weekend;
import nl.novi.gettogetherbackend.services.ActivityService;
import nl.novi.gettogetherbackend.services.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.novi.gettogetherbackend.dtos.ActivityResponseDTO;
import nl.novi.gettogetherbackend.mappers.ActivityMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Checked

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;
    private final ImageService imageService;

    public ActivityController(ActivityService activityService, ImageService imageService) {
        this.activityService = activityService;
        this.imageService = imageService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        var result = activityService.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/weekend/{weekendId}")
    public ResponseEntity<List<ActivityResponseDTO>> getActivities( @PathVariable Long weekendId)
    {
        return ResponseEntity.ok(ActivityMapper.toResponseDTOList(activityService.findAllByWeekendId(weekendId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> findById(
            @PathVariable Long id) {

        Optional<Activity> activityOptional= activityService.findById(id);

        if (activityOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ActivityResponseDTO responseDTO = ActivityMapper.toResponseDTO(activityOptional.get());

        return ResponseEntity.ok(responseDTO);
    }

    //upload
    @PostMapping("/{id}/image")
    public ResponseEntity<Activity> addImageToActivity(@PathVariable("id") Long activityId,
                                                     @RequestParam("file") MultipartFile file                                    )
            throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/activity/")
                .path(Objects.requireNonNull(activityId.toString()))
                .path("/image")
                .toUriString();
        String fileName = imageService.storeImage(file, activityId);
        Activity activity = activityService.addImageToActivity(fileName, activityId);

        return ResponseEntity.created(URI.create(url)).body(activity);

    }

    //download
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getActivityImage(@PathVariable("id") Long id, HttpServletRequest request){
        Resource resource = activityService.getImageFromActivity(id);

        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (mimeType == null) {
                mimeType = "image/jpeg";
            }
        } catch (IOException e) {
            mimeType = "image/jpeg";
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

}