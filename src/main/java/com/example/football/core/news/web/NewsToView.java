package com.example.football.core.news.web;

import com.example.football.core.news.News;
import com.example.football.core.team.Team;
import com.example.football.core.team.web.TeamConverter;
import com.example.football.core.team.web.TeamView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class NewsToView implements Converter<News,NewsView> {

    private final TeamConverter converter;

    public NewsToView(TeamConverter converter) {
        this.converter = converter;
    }

    @Override
    public NewsView convert(@NonNull News news) {
        NewsView view = new NewsView();
        view.setId(news.getId());
        view.setName(news.getName());
        view.setContent(news.getContent());
        Set<TeamView> views = new HashSet<>();
        Set<Team> teams= news.getTeams();
        teams.forEach(team -> views.add(converter.toView(team)));
        view.setTeams(views);
        return view;
    }
}
