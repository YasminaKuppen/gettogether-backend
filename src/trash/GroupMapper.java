//package nl.novi.gettogetherbackend.mappers;
//
//import nl.novi.gettogetherbackend.dtos.GroupCreateDTO;
//import nl.novi.gettogetherbackend.dtos.GroupResponseDTO;
//import nl.novi.gettogetherbackend.models.Group;
//import nl.novi.gettogetherbackend.models.User;
//import nl.novi.gettogetherbackend.models.Weekend;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class GroupMapper {
//
//
//    public static GroupResponseDTO toResponseDTO(Group group) {
//        var result = new GroupResponseDTO();
//        result.setId(group.getId());
//        result.setUser(group.getUser());
//        result.setWeekend(group.getWeekend());
//        return result;
//    }
//
//    public static Group toEntity(GroupCreateDTO groupCreateDTO) {
//        Group group = new Group();
//        group.setId(groupCreateDTO.getId());
//        group.setWeekend(groupCreateDTO.getWeekend());
//        group.setUser(groupCreateDTO.getUser());
//        return group;
//    }
//
//    public static List<GroupResponseDTO> toResponseDTOList(List<Group> group) {
//        return group.stream().map(GroupMapper::toResponseDTO).collect(Collectors.toList());
//    }
//}
