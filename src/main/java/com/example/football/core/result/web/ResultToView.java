package com.example.football.core.result.web;

import com.example.football.core.result.Result;
import com.example.football.core.team.Team;
import com.example.football.core.team.web.TeamConverter;
import com.example.football.core.team.web.TeamView;
import com.example.football.core.tournament.Tournament;
import com.example.football.core.tournament.web.TournamentConverter;
import com.example.football.core.tournament.web.TournamentView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ResultToView implements Converter<Result,ResultView> {
    private final TeamConverter converter;
    private final TournamentConverter converterTournament;

    public ResultToView(TeamConverter converter,
                        TournamentConverter converterTournament) {
        this.converter = converter;
        this.converterTournament = converterTournament;
    }

    @Override
    public ResultView convert(@NonNull Result result) {
        ResultView view = new ResultView();
        view.setId(result.getId());
        view.setDraw(result.getDraw());
        view.setGoals(result.getGoals());
        view.setGoalsMissed(result.getGoalsMissed());
        view.setLoses(result.getLoses());
        view.setMissed(result.getMissed());
        view.setPlace(result.getPlace());
        view.setWins(result.getWins());
        view.setPoints(result.getPoints());
        Team team = result.getTeam();
        TeamView teamView = converter.toView(team);
        view.setTeam(teamView);
        Tournament tournament = result.getTournament();
        TournamentView tournamentView = converterTournament.toView(tournament);
        view.setTournament(tournamentView);
        return view;
    }
}
