package com.example.football.core.match;

import com.example.football.core.match.web.MatchConverter;
import com.example.football.core.match.web.MatchView;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{

    private final MatchRepo matchRepo;
    private final MatchConverter converter;

    public MatchServiceImpl(MatchRepo matchRepo,
                            MatchConverter converter) {
        this.matchRepo = matchRepo;
        this.converter = converter;
    }

    public Match findMatchOrThrow(Long id) {
        if(matchRepo.existsById(id)) {
            return matchRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Match not found"));
        }
        return null;
    }

    @Override
    public MatchView findMatchViewOrThrow(Long id) {
        if(matchRepo.existsById(id)) {
            Match match = matchRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Match not found"));
            return converter.toView(match);
        }
        return null;
    }

    public List<MatchView> findAllMatch(){
        List<Match> matches = matchRepo.findAll();
        return converter.toViews(matches);
    }

    public MatchView create(Match match) {
        Match matchSave = matchRepo.save(match);
        return converter.toView(matchSave);
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
