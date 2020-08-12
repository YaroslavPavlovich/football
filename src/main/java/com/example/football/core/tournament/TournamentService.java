package com.example.football.core.tournament;

import com.example.football.base.BaseRequest;
import com.example.football.core.team.Team;
import com.example.football.core.team.TeamRepo;
import com.example.football.core.tournament.web.TournamentConverter;
import com.example.football.core.tournament.web.TournamentView;
import com.example.football.core.tournament.web.request.TournamentBaseReq;
import com.example.football.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    private final TournamentRepo tournamentRepo;
    private final TournamentConverter converter;
    private final TeamRepo teamRepo;

    public TournamentService(final TournamentRepo tournamentRepo,
                             final TeamRepo teamRepo,
                             final TournamentConverter converter) {
        this.tournamentRepo = tournamentRepo;
        this.teamRepo = teamRepo;
        this.converter =converter;
    }

    public TournamentView findTournamentViewOrThrow(Long id) {
        Tournament tournament = tournamentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        return converter.toView(tournament);
    }

    public Tournament findTournamentOrThrow(Long id) {
        return tournamentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
    }

    public Page<TournamentView> findAllTournament(Pageable pageable){
        Page<Tournament> tournaments = tournamentRepo.findAll(pageable);
        List<TournamentView> views = converter.toViews(tournaments);
        return new PageImpl<>(views, pageable, tournaments.getTotalElements());
    }

    public TournamentView create(TournamentBaseReq req) {
        Tournament tournament = new Tournament();
        this.prepare(tournament,req);
        Tournament tournamentSave = tournamentRepo.save(tournament);
        return converter.toView(tournamentSave);
    }

    public boolean delete(Long id){
        if(tournamentRepo.existsById(id)) {
            tournamentRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public TournamentView update(Tournament tournament, TournamentBaseReq req){
        Tournament newTournament = this.prepare(tournament,req);
        Tournament tournamentSave = tournamentRepo.save(newTournament);
        return converter.toView(tournamentSave);
    }

    public Tournament prepare(Tournament tournament, TournamentBaseReq tournamentBaseReq){
        tournament.setName(tournamentBaseReq.getName());
        tournament.setCountry(tournamentBaseReq.getCountry());
        List<Team> teamList = teamRepo.findAllById(tournamentBaseReq.getTeams()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Team> teams = new HashSet<>(teamList);
        tournament.setTeams(teams);
        return tournament;
    }
}
