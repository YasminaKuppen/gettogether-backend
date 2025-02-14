package nl.novi.gettogetherbackend.mappers;

import nl.novi.gettogetherbackend.dtos.VoteCreateDTO;
import nl.novi.gettogetherbackend.dtos.VoteResponseDTO;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.repositories.ActivityRepository;
import nl.novi.gettogetherbackend.repositories.UserRepository;


public class VoteMapper {


    public static VoteResponseDTO toResponseDTO(Vote vote) {
        var result = new VoteResponseDTO();
        result.setActivityId(vote.getActivityId());
        result.setUserId(vote.getUserId());
        result.setVotes(vote.getVotes());
        return result;
    }

    public static Vote toEntity(VoteCreateDTO voteCreateDTO, UserRepository userRepository, ActivityRepository activityRepository) {
        Vote vote = new Vote();
        vote.setUser(userRepository.findById(voteCreateDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        vote.setActivity(activityRepository.findById(voteCreateDTO.getActivityId()).orElseThrow(() -> new RuntimeException("Activity not found")));
        vote.setVotes(1);
        return vote;
    }

}
