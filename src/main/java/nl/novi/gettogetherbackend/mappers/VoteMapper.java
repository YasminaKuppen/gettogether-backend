package nl.novi.gettogetherbackend.mappers;

import nl.novi.gettogetherbackend.dtos.VoteCreateDTO;
import nl.novi.gettogetherbackend.dtos.VoteResponseDTO;
import nl.novi.gettogetherbackend.models.Vote;

import java.util.List;
import java.util.stream.Collectors;

public class VoteMapper {


    public static VoteResponseDTO toResponseDTO(Vote vote) {
        var result = new VoteResponseDTO();
        result.setId(vote.getId());
        result.setUser(vote.getUser());
        result.setActivity(vote.getActivity());
        result.setVotes(vote.getVotes());
        return result;
    }

    public static Vote toEntity(VoteCreateDTO voteCreateDTO) {
        Vote vote = new Vote();
        vote.setUser(voteCreateDTO.getUser());
        vote.setActivity(voteCreateDTO.getActivity());
        vote.setVotes(1);
        return vote;
    }


    public static List<VoteResponseDTO> toResponseDTOList(List<Vote> vote) {
        return vote.stream().map(VoteMapper::toResponseDTO).collect(Collectors.toList());
    }
}
