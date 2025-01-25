package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.VoteCreateDTO;
import nl.novi.gettogetherbackend.mappers.VoteMapper;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.services.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        }
        Vote vote = VoteMapper.toEntity(voteCreateDTO);
        Vote savedVote = voteService.save(vote);
        return ResponseEntity.status(HttpStatus.CREATED).body(VoteMapper.toResponseDTO(savedVote));

    }
}