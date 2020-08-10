package com.example.football.core.team;

import com.example.football.core.team.web.TeamConverter;
import com.example.football.core.team.web.TeamView;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepo teamRepo;
    private final TeamConverter converter;

    public TeamServiceImpl(TeamRepo teamRepo, TeamConverter converter) {
        this.teamRepo = teamRepo;
        this.converter = converter;
    }

    @Override
    public TeamView findTeamViewOrThrow(Long id) {
        if(teamRepo.existsById(id)) {
            Team team =  teamRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Team not found"));
            return converter.toView(team);
        }
        return null;
    }

    public Team findTeamOrThrow(Long id) {
        if(teamRepo.existsById(id)) {
            return teamRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        }
        return null;
    }

    public List<TeamView> findAllTeam(){
        List<Team> teams = teamRepo.findAll();
        return converter.toViews(teams);
    }

    public TeamView create(Team team) {
        Team teamSave = teamRepo.save(team);
        return converter.toView(teamSave);
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
