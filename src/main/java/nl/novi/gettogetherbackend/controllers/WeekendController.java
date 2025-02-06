package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.WeekendResponseDTO;
import nl.novi.gettogetherbackend.dtos.WeekendCreateDTO;
import nl.novi.gettogetherbackend.dtos.WeekendResponseDTO;
import nl.novi.gettogetherbackend.mappers.WeekendMapper;
import nl.novi.gettogetherbackend.mappers.WeekendMapper;
import nl.novi.gettogetherbackend.models.*;
import nl.novi.gettogetherbackend.models.Weekend;
import nl.novi.gettogetherbackend.services.WeekendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import nl.novi.gettogetherbackend.repositories.WeekendRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weekends")
public class WeekendController {
    private final WeekendService weekendService;

    public WeekendController(WeekendService weekendService) {
        this.weekendService = weekendService;
    }

    @PostMapping
    public ResponseEntity<?> createWeekend(@Valid @RequestBody WeekendCreateDTO weekendCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Weekend weekend = WeekendMapper.toEntity(weekendCreateDTO);
        Weekend savedWeekend = weekendService.save(weekend);
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
            weekend.setAddedBy(weekendDetails.getAddedBy());
            Weekend updatedWeekend = weekendService.save(weekend);
            return ResponseEntity.ok(WeekendMapper.toResponseDTO(updatedWeekend));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeekend(@PathVariable Long id) {
        var result = weekendService.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
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
    
    

    @GetMapping
    public ResponseEntity<List<WeekendResponseDTO>> getWeekends(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Date date,
            @RequestParam(required = false) String time,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer temperature,
            @RequestParam(required = false) User addedBy,
            @RequestParam(required = false) List<Group> groups,
            @RequestParam(required = false) List<Activity> activities
       )

    {
        // Controleer of temperature null is, zo ja, gebruik een standaardwaarde
        if (temperature == null) {
            temperature = 20;  // Of kies een andere standaardwaarde
        }
        return ResponseEntity.ok(WeekendMapper.toResponseDTOList(weekendService.getWeekends(name, date, time, location, temperature, addedBy, groups, activities)));
    }

}