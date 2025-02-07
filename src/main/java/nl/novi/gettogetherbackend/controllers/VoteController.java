package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.VoteCreateDTO;
import nl.novi.gettogetherbackend.mappers.VoteMapper;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.repositories.ActivityRepository;
import nl.novi.gettogetherbackend.repositories.UserRepository;
import nl.novi.gettogetherbackend.services.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// Checked

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
    public ResponseEntity<?> createVote(@Valid @RequestBody VoteCreateDTO voteCreateDTO) {
        voteService.createVote(voteCreateDTO.getUserId(), voteCreateDTO.getActivityId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}