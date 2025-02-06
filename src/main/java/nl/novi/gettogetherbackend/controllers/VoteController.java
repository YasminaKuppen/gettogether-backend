package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.VoteCreateDTO;
import nl.novi.gettogetherbackend.dtos.VoteResponseDTO;
import nl.novi.gettogetherbackend.mappers.VoteMapper;
import nl.novi.gettogetherbackend.models.Activity;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.services.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<?> createVote(@Valid @RequestBody VoteCreateDTO voteCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }  System.out.println("Payload ontvangen: " + result);
        Vote vote = VoteMapper.toEntity(voteCreateDTO);
        Vote savedVote = voteService.save(vote);
        return ResponseEntity.status(HttpStatus.CREATED).body(VoteMapper.toResponseDTO(savedVote));

    }

//    @GetMapping
//    public ResponseEntity<List<VoteResponseDTO>> getVotes(
//            @RequestParam(required = false) User user,
//            @RequestParam(required = false) Activity activity,
//            @RequestParam(required = false) Integer votes
//    )
//
//    {
//
//        return ResponseEntity.ok(VoteMapper.toResponseDTOList(voteService.getVotes(user, activity, votes)));
//    }
}