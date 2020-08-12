package com.example.football.core.team.web;

import com.example.football.core.team.Team;
import org.springframework.data.domain.Page;
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

    public List<TeamView> toViews(Page<Team> teamPage) {
        List<TeamView> views = new ArrayList<TeamView>();
        List<Team> teams = teamPage.getContent();
        teams.forEach(team -> views.add(toView(team)));
        return views;
    }
}
