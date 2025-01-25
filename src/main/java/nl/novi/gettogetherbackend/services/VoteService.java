package nl.novi.gettogetherbackend.services;

import nl.novi.gettogetherbackend.models.Activity;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.repositories.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    public Optional<Vote> findById(Long id) {
        return voteRepository.findById(id);
    }

    public boolean delete(long id) {
        if (voteRepository.existsById(id)) {
            voteRepository.deleteById(id);
            return true;

        } else {
            return false;
        }
    }

    public List<Vote> getVotes(
            User user,
            Activity activity,
            Integer votes
    ) {
        List<Vote> getVotes;
        getVotes = voteRepository.findAll();
        return getVotes;
    }
}