package com.example.football.core.match;

import com.example.football.core.match.web.MatchConverter;
import com.example.football.core.match.web.MatchView;
import com.example.football.core.match.web.request.MatchBaseReq;
import com.example.football.core.match.web.request.MatchLastReq;
import com.example.football.core.team.TeamRepo;
import com.example.football.core.tournament.TournamentRepo;
import com.example.football.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MatchService {

    private final MatchRepo matchRepo;
    private final MatchConverter converter;
    private final TeamRepo teamRepo;
    private final TournamentRepo tournamentRepo;

    public MatchService(MatchRepo matchRepo,
                        MatchConverter converter,
                        TeamRepo teamRepo,
                        TournamentRepo tournamentRepo) {
        this.matchRepo = matchRepo;
        this.converter = converter;
        this.teamRepo = teamRepo;
        this.tournamentRepo = tournamentRepo;
    }

    public List<MatchView> findLastMatch(MatchLastReq req){
        List<Match> match = matchRepo.getLastMatch(req.getPlayerSurname(), req.getPlayerName());
        return converter.toViewsList(match);
    }

    public Match findMatchOrThrow(Long id) {
        return matchRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match not found"));
    }

    public MatchView findMatchViewOrThrow(Long id) {
        Match match = matchRepo.findById(id)
                   .orElseThrow(() -> new EntityNotFoundException("Match not found"));
        return converter.toView(match);
    }

    public Page<MatchView> findAllMatch(Pageable pageable){
        Page<Match> matches = matchRepo.findAll(pageable);
        List<MatchView> views = converter.toViews(matches);
        return new PageImpl<>(views, pageable, matches.getTotalElements());
    }

    public MatchView create(MatchBaseReq req) {
        Match match = new Match();
        this.prepare(match,req);
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

    public MatchView update(Match match, MatchBaseReq req){
        Match newMatch = this.prepare(match,req);
        Match matchSave = matchRepo.save(newMatch);
        return converter.toView(matchSave);
    }

    private Match prepare(Match match, MatchBaseReq req) {
        match.setMatchDate(req.getMatchDate());
        match.setScoreOwners(req.getScoreOwners());
        match.setScoreGuests(req.getScoreGuests());
        match.setTournament(tournamentRepo.getOne(req.getTournamentId()));
        match.setOwner(teamRepo.getOne(req.getOwnerId()));
        match.setGuest(teamRepo.getOne(req.getGuestId()));
        return match;
    }
}
