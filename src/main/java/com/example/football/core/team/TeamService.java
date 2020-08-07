package com.example.football.core.team;

import java.util.List;

public interface TeamService {
    Team findTeamOrThrow(Long id);

    List<Team> findAllTeam();

    Team create(Team req);

    boolean delete(Long id);

    boolean update(Team team, Long id);
}
