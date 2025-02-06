package nl.novi.gettogetherbackend.mappers;

import nl.novi.gettogetherbackend.dtos.ActivityCreateDTO;
import nl.novi.gettogetherbackend.dtos.ActivityResponseDTO;
import nl.novi.gettogetherbackend.models.Activity;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityMapper {


    public static ActivityResponseDTO toResponseDTO(Activity activity) {
        var result = new ActivityResponseDTO();
        result.setId(activity.getId());
        result.setTitle(activity.getTitle());
        result.setDescription(activity.getDescription());
        result.setLocation(activity.getLocation());
        result.setCosts(activity.getCosts());
        result.setAddedBy(activity.getAddedBy());
        result.setWeekend(activity.getWeekend());
        result.setVotes(activity.getVotes());
        return result;
    }

    public static Activity toEntity(ActivityCreateDTO activityCreateDTO) {
        Activity activity = new Activity();
        activity.setTitle(activityCreateDTO.getTitle());
        activity.setDescription(activityCreateDTO.getDescription());
        activity.setLocation(activityCreateDTO.getLocation());
        activity.setCosts(activityCreateDTO.getCosts());
        activity.setAddedBy(activityCreateDTO.getAddedBy());
        activity.setWeekend(activityCreateDTO.getWeekend());
        return activity;
    }


    public static List<ActivityResponseDTO> toResponseDTOList(List<Activity> activity) {
        return activity.stream().map(ActivityMapper::toResponseDTO).collect(Collectors.toList());
    }
}
