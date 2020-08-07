package com.example.football.core.match;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{

    private final MatchRepo matchRepo;

    public MatchServiceImpl(MatchRepo matchRepo) {
        this.matchRepo = matchRepo;
    }

    public Match findMatchOrThrow(Long id) {
        if(matchRepo.existsById(id)) {
            return matchRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Match not found"));
        }
        return null;
    }

    public List<Match> findAllMatch(){
        return matchRepo.findAll();
    }

    public Match create(Match match) {
        return matchRepo.save(match);
    }

    public boolean delete(Long id){
        if(matchRepo.existsById(id)) {
            matchRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean update(Match match, Match matchUpdate){
        matchUpdate.setScoreGuests(match.getScoreGuests());
        matchUpdate.setScoreOwners(match.getScoreOwners());
        matchUpdate.setMatchDate(matchUpdate.getMatchDate());
        if (match.getGuest() != null) {
            matchUpdate.setGuest(match.getGuest());
        }
        if (match.getOwner() != null) {
            matchUpdate.setOwner(match.getOwner());
        }
        if (match.getTournament() != null) {
            matchUpdate.setTournament(match.getTournament());
        }
        matchRepo.save(matchUpdate);
        return true;
    }
}
