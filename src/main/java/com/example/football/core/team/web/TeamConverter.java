package com.example.football.core.team.web;

import com.example.football.core.team.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamConverter {
    private final TeamToView toView;

    public TeamConverter (TeamToView toView) {
        this.toView = toView;
    }

    public TeamView toView(Team team) {
        return toView.convert(team);
    }

    public List<TeamView> toViews(List<Team> teams) {
        List<TeamView> views = new ArrayList<TeamView>();
        teams.forEach(team -> views.add(toView(team)));
        return views;
    }
}
