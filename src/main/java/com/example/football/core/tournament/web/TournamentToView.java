package com.example.football.core.tournament.web;

import com.example.football.core.team.Team;
import com.example.football.core.team.web.TeamConverter;
import com.example.football.core.team.web.TeamView;
import com.example.football.core.tournament.Tournament;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TournamentToView implements Converter<Tournament,TournamentView> {

    private final TeamConverter converter;

    public TournamentToView(TeamConverter converter) {
        this.converter = converter;
    }

    @Override
    public TournamentView convert(@NonNull Tournament tournament) {
        TournamentView view = new TournamentView();
        view.setId(tournament.getId());
        view.setName(tournament.getName());
        view.setCountry(tournament.getCountry());
        Set<TeamView> views = new HashSet<>();
        Set<Team> teams= tournament.getTeams();
        teams.forEach(team -> views.add(converter.toView(team)));
        view.setTeams(views);
        return view;
    }
}
