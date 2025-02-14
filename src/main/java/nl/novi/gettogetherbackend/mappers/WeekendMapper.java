package nl.novi.gettogetherbackend.mappers;

import nl.novi.gettogetherbackend.dtos.WeekendCreateDTO;
import nl.novi.gettogetherbackend.dtos.WeekendResponseDTO;
import nl.novi.gettogetherbackend.models.Weekend;

import java.util.List;
import java.util.stream.Collectors;

public class WeekendMapper {


    public static WeekendResponseDTO toResponseDTO(Weekend weekend) {
        var result = new WeekendResponseDTO();
        result.setId(weekend.getId());
        result.setName(weekend.getName());
        result.setDate(weekend.getDate());
        result.setTime(weekend.getTime());
        result.setLocation(weekend.getLocation());
        result.setTemperature(weekend.getTemperature());
        result.setAddedBy(weekend.getAddedBy());
        result.setAddedById(weekend.getAddedById());
        return result;
    }

    public static Weekend toEntity(WeekendCreateDTO weekendCreateDTO) {
        Weekend weekend = new Weekend();
        weekend.setName(weekendCreateDTO.getName());
        weekend.setDate(weekendCreateDTO.getDate());
        weekend.setTime(weekendCreateDTO.getTime());
        weekend.setLocation(weekendCreateDTO.getLocation());
        weekend.setTemperature(weekendCreateDTO.getTemperature());
        weekend.setAddedBy(weekendCreateDTO.getAddedBy());
        return weekend;
    }


    public static List<WeekendResponseDTO> toResponseDTOList(List<Weekend> weekend) {
        return weekend.stream().map(WeekendMapper::toResponseDTO).collect(Collectors.toList());
    }
}
