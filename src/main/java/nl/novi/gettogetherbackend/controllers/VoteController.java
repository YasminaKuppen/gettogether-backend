package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.VoteCreateDTO;
import nl.novi.gettogetherbackend.mappers.VoteMapper;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.repositories.ActivityRepository;
import nl.novi.gettogetherbackend.repositories.UserRepository;
import nl.novi.gettogetherbackend.services.VoteService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/votes")
public class VoteController {
    private final VoteService voteService;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public VoteController(VoteService voteService, UserRepository userRepository, ActivityRepository activityRepository) {
        this.voteService = voteService;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @PostMapping()
    public ResponseEntity<String> createVote(@Valid @RequestBody VoteCreateDTO voteCreateDTO) {
        try {
            Vote vote = VoteMapper.toEntity(voteCreateDTO, userRepository, activityRepository);
            voteService.save(vote);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vote cast succesfully.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("You have already voted for this activity!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }


}