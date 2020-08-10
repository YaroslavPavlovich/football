package com.example.football.core.tournament;

import com.example.football.core.team.Team;
import com.example.football.core.tournament.web.TournamentConverter;
import com.example.football.core.tournament.web.TournamentView;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepo tournamentRepo;
    private final TournamentConverter converter;

    public TournamentServiceImpl(final TournamentRepo tournamentRepo,
                                 final TournamentConverter converter) {
        this.tournamentRepo = tournamentRepo;
        this.converter =converter;
    }

    public TournamentView findTournamentViewOrThrow(Long id) {
        if(tournamentRepo.existsById(id)) {
            Tournament tournament = tournamentRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
            return converter.toView(tournament);
        }
        return null;
    }

    @Override
    public Tournament findTournamentOrThrow(Long id) {
        if(tournamentRepo.existsById(id)) {
            return tournamentRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        }
        return null;
    }

    public List<TournamentView> findAllTournament(){
        List<Tournament> tournaments = tournamentRepo.findAll();
        return converter.toViews(tournaments);
    }

    public TournamentView create(Tournament tournament) {
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

    public boolean update(Tournament tournament, Tournament tournamentUpdate){
        tournamentUpdate.setCountry(tournament.getCountry());
        tournamentUpdate.setName(tournament.getName());
        if (tournament.getTeams().size()!= 0) {
            tournamentUpdate.setTeams(tournament.getTeams());
        }
        tournamentRepo.save(tournamentUpdate);
        return true;
    }
}
