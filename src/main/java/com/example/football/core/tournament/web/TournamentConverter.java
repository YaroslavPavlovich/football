package com.example.football.core.tournament.web;

import com.example.football.core.tournament.Tournament;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TournamentConverter {
    private final TournamentToView toView;

    public TournamentConverter(TournamentToView toView) {
        this.toView = toView;
    }

    public TournamentView toView(Tournament tournament) {
        return toView.convert(tournament);
    }

    public List<TournamentView> toViews(List<Tournament> tournaments) {
        List<TournamentView> views = new ArrayList<TournamentView>();
        tournaments.forEach(tournament -> views.add(toView(tournament)));
        return views;
    }
}
