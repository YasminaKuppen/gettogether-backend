package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.WeekendCreateDTO;
import nl.novi.gettogetherbackend.dtos.WeekendResponseDTO;
import nl.novi.gettogetherbackend.mappers.WeekendMapper;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeekend(@PathVariable Long id) {
        var result = weekendService.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<WeekendResponseDTO>> getWeekends(
            @RequestParam(required = false) Date date,
            @RequestParam(required = false) String time,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) int temperature
       )

    {

        return ResponseEntity.ok(WeekendMapper.toResponseDTOList(weekendService.getWeekends(date, time, location, temperature)));
    }

}