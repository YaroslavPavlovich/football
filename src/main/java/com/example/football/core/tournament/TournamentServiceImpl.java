package com.example.football.core.tournament;

import com.example.football.core.team.Team;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepo tournamentRepo;

    public TournamentServiceImpl(final TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    public Tournament findTournamentOrThrow(Long id) {
        if(tournamentRepo.existsById(id)) {
            return tournamentRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        }
        return null;
    }

    public List<Tournament> findAllTournament(){
        return tournamentRepo.findAll();
    }

    public Tournament create(Tournament tournament) {
        return tournamentRepo.save(tournament);
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
