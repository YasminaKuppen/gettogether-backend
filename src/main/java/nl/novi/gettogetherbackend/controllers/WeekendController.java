package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.WeekendResponseDTO;
import nl.novi.gettogetherbackend.dtos.WeekendCreateDTO;
import nl.novi.gettogetherbackend.mappers.WeekendMapper;
import nl.novi.gettogetherbackend.models.*;
import nl.novi.gettogetherbackend.models.Weekend;
import nl.novi.gettogetherbackend.services.GroupService;
import nl.novi.gettogetherbackend.services.WeekendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// Checked

@RestController
@RequestMapping("/weekends")
public class WeekendController {
    private final WeekendService weekendService;
    private final GroupService groupService;

    public WeekendController(WeekendService weekendService, GroupService groupService) {
        this.weekendService = weekendService;
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<?> createWeekend(@Valid @RequestBody WeekendCreateDTO weekendCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Weekend weekend = WeekendMapper.toEntity(weekendCreateDTO);
        Weekend savedWeekend = weekendService.save(weekend);

        // Create new group for the new weekend with creator as member:
        Group group = new Group(savedWeekend);
        group.addUser(savedWeekend.getAddedBy());
        groupService.save(group);

        return ResponseEntity.status(HttpStatus.CREATED).body(WeekendMapper.toResponseDTO(savedWeekend));


    }

    @PutMapping("/{id}")
    public ResponseEntity<WeekendResponseDTO> updateWeekend(@PathVariable Long id, @RequestBody Weekend weekendDetails) {
        Optional<Weekend> weekendOptional = weekendService.findById(id);
        if (weekendOptional.isPresent()) {
            Weekend weekend = weekendOptional.get();
            weekend.setName(weekendDetails.getName());
            weekend.setDate(weekendDetails.getDate());
            weekend.setTime(weekendDetails.getTime());
            weekend.setLocation(weekendDetails.getLocation());
            weekend.setTemperature(weekendDetails.getTemperature());
            Weekend updatedWeekend = weekendService.save(weekend);
            return ResponseEntity.ok(WeekendMapper.toResponseDTO(updatedWeekend));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeekendResponseDTO> getWeekendById(@PathVariable Long id) {
        Optional<Weekend> weekendOptional = weekendService.findById(id);
        if (weekendOptional.isPresent()) {
            Weekend weekend = weekendOptional.get();
            WeekendResponseDTO weekendResponseDTO = WeekendMapper.toResponseDTO(weekend);
            return ResponseEntity.ok(weekendResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}