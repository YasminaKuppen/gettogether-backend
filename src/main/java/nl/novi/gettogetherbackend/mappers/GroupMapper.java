package nl.novi.gettogetherbackend.mappers;

import nl.novi.gettogetherbackend.dtos.GroupCreateDTO;
import nl.novi.gettogetherbackend.dtos.GroupResponseDTO;
import nl.novi.gettogetherbackend.models.Group;

import java.util.List;
import java.util.stream.Collectors;

public class GroupMapper {


    public static GroupResponseDTO toResponseDTO(Group group) {
        var result = new GroupResponseDTO();
        result.setId(group.getId());
        result.setType(group.getType());
        result.setCreator(group.getCreator());
        return result;
    }

    public static Group toEntity(GroupCreateDTO groupCreateDTO) {
        Group group = new Group();
        group.setType(groupCreateDTO.getType());
        group.setCreator(groupCreateDTO.getCreator());
        return group;
    }


    public static List<GroupResponseDTO> toResponseDTOList(List<Group> group) {
        return group.stream().map(GroupMapper::toResponseDTO).collect(Collectors.toList());
    }
}
