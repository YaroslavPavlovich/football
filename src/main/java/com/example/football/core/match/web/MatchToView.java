package com.example.football.core.match.web;

import com.example.football.core.match.Match;
import com.example.football.core.team.Team;
import com.example.football.core.team.web.TeamConverter;
import com.example.football.core.tournament.Tournament;
import com.example.football.core.tournament.web.TournamentConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MatchToView implements Converter<Match, MatchView> {
    private final TeamConverter converter;
    private final TournamentConverter converterTournament;

    public MatchToView(TeamConverter converter,
                       TournamentConverter converterTournament) {
        this.converter = converter;
        this.converterTournament = converterTournament;
    }

    @Override
    public MatchView convert(@NonNull Match match) {
        MatchView view = new MatchView();
        view.setId(match.getId());
        view.setMatchDate(match.getMatchDate());
        view.setScoreOwners(match.getScoreOwners());
        view.setScoreGuests(match.getScoreGuests());
        Team guest = match.getGuest();
        view.setGuest(converter.toView(guest));
        Team owner = match.getOwner();
        view.setOwner(converter.toView(owner));
        Tournament tournament = match.getTournament();
        view.setTournament(converterTournament.toView(tournament));
        return view;
    }
}
