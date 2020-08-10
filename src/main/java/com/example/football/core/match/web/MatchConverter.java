package com.example.football.core.match.web;

import com.example.football.core.match.Match;
import com.example.football.core.tournament.web.TournamentView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchConverter {
    private final MatchToView toView;

    public MatchConverter(MatchToView toView) {
        this.toView = toView;
    }

    public MatchView toView(Match match) {
        return toView.convert(match);
    }

    public List<MatchView> toViews(List<Match> matches) {
        List<MatchView> views = new ArrayList<MatchView>();
        matches.forEach(match -> views.add(toView(match)));
        return views;
    }
}
