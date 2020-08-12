package com.example.football.core.team;

import com.example.football.base.BaseRequest;
import com.example.football.core.team.web.TeamConverter;
import com.example.football.core.team.web.TeamView;
import com.example.football.core.team.web.request.TeamBaseReq;
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
public class TeamService {
    private final TeamRepo teamRepo;
    private final TeamConverter converter;

    public TeamService(TeamRepo teamRepo, TeamConverter converter) {
        this.teamRepo = teamRepo;
        this.converter = converter;
    }

    public TeamView findTeamViewOrThrow(Long id) {
        Team team =  teamRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        return converter.toView(team);
    }

    public Team findTeamOrThrow(Long id) {
        return teamRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
    }

    public Page<TeamView> findAllTeam(Pageable pageable){
        Page<Team> teams = teamRepo.findAll(pageable);
        List<TeamView> views = converter.toViews(teams);
        return new PageImpl<>(views, pageable, teams.getTotalElements());
    }

    public TeamView create(TeamBaseReq req) {
        Team team = new Team();
        this.prepare(team,req);
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

    public TeamView update(Team team, TeamBaseReq req){
        Team newTeam = this.prepare(team,req);
        Team tournamentSave = teamRepo.save(newTeam);
        return converter.toView(tournamentSave);
    }

    public Team prepare(Team team, TeamBaseReq teamBaseReq){
        team.setName(teamBaseReq.getName());
        team.setNumPlayers(teamBaseReq.getNumPlayers());
        return team;
    }
}
