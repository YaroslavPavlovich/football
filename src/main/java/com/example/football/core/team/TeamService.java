package com.example.football.core.team;

import com.example.football.core.team.web.TeamView;

import java.util.List;

public interface TeamService {
    TeamView findTeamViewOrThrow(Long id);

    Team findTeamOrThrow(Long id);

    List<TeamView> findAllTeam();

    TeamView create(Team req);

    boolean delete(Long id);

    boolean update(Team team, Long id);
}
