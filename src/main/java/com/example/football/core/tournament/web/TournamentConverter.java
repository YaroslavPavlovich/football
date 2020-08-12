package com.example.football.core.tournament.web;

import com.example.football.core.tournament.Tournament;
import org.springframework.data.domain.Page;
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

    public List<TournamentView> toViews(Page<Tournament> tournamentPage) {
        List<TournamentView> views = new ArrayList<TournamentView>();
        List<Tournament> tournaments = tournamentPage.getContent();
        tournaments.forEach(tournament -> views.add(toView(tournament)));
        return views;
    }
}
