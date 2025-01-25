package nl.novi.gettogetherbackend.mappers;

import nl.novi.gettogetherbackend.dtos.UserCreateDTO;
import nl.novi.gettogetherbackend.dtos.UserResponseDTO;
import nl.novi.gettogetherbackend.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(User user) {
        var result = new UserResponseDTO();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setEmail(user.getEmail());
        return result;
    }

    public static User toEntity(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());
        user.setRole("USER");
        return user;
    }


    public static List<UserResponseDTO> toResponseDTOList(List<User> user) {
        return user.stream().map(nl.novi.gettogetherbackend.mappers.UserMapper::toResponseDTO).collect(Collectors.toList());
    }
}
