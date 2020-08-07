package com.example.football.core.team;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepo teamRepo;

    public TeamServiceImpl(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    public Team findTeamOrThrow(Long id) {
        if(teamRepo.existsById(id)) {
            return teamRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        }
        return null;
    }

    public List<Team> findAllTeam(){
        return teamRepo.findAll();
    }

    public Team create(Team team) {
        return teamRepo.save(team);
    }

    public boolean delete(Long id){
        if(teamRepo.existsById(id)) {
            teamRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean update(Team team, Long id){
        if(teamRepo.existsById(id)) {
            team.setId(id);
            teamRepo.save(team);
            return true;
        }
        return false;
    }
}
