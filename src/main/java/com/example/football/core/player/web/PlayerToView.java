package com.example.football.core.player.web;

import com.example.football.core.player.Player;
import com.example.football.core.team.Team;
import com.example.football.core.team.web.TeamConverter;
import com.example.football.core.team.web.TeamView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PlayerToView implements Converter<Player,PlayerView> {
    private final TeamConverter converter;

    public PlayerToView(TeamConverter converter) {
        this.converter = converter;
    }

    @Override
    public PlayerView convert(@NonNull Player player) {
        PlayerView view = new PlayerView();
        view.setId(player.getId());
        view.setName(player.getName());
        view.setSurname(player.getSurname());
        view.setAge(player.getAge());
        view.setHeight(player.getHeight());
        view.setWeight(player.getWeight());
        Set<TeamView> views = new HashSet<>();
        Set<Team> teams= player.getTeams();
        teams.forEach(team -> views.add(converter.toView(team)));
        view.setTeams(views);
        return view;
    }
}
