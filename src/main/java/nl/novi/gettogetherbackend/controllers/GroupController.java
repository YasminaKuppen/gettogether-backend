package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.GroupCreateDTO;
import nl.novi.gettogetherbackend.dtos.GroupResponseDTO;
import nl.novi.gettogetherbackend.mappers.GroupMapper;
import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Weekend;
import nl.novi.gettogetherbackend.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import nl.novi.gettogetherbackend.repositories.GroupRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupCreateDTO groupCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Group group = GroupMapper.toEntity(groupCreateDTO);
        Group savedGroup = groupService.save(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(GroupMapper.toResponseDTO(savedGroup));

    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> updateGroup(@PathVariable Long id, @RequestBody Group groupDetails) {
        Optional<Group> groupOptional = groupService.findById(id);
        if (groupOptional.isPresent()) {
            Group group = groupOptional.get();
            group.setUser(groupDetails.getUser());
            group.setWeekend(groupDetails.getWeekend());
            Group updatedGroup = groupService.save(group);
            return ResponseEntity.ok(GroupMapper.toResponseDTO(updatedGroup));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        var result = groupService.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GroupResponseDTO>> getGroups(
            @RequestParam(required = false) Weekend weekend,
            @RequestParam(required = false) User user
    )

    {

        return ResponseEntity.ok(GroupMapper.toResponseDTOList(groupService.getGroups(weekend, user)));
    }

}