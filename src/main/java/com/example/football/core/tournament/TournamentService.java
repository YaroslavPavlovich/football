package com.example.football.core.tournament;

import com.example.football.core.tournament.web.TournamentView;
import com.example.football.core.tournament.web.request.TournamentCreateReq;

import java.util.List;

public interface TournamentService {
    TournamentView findTournamentViewOrThrow(Long id);

    Tournament findTournamentOrThrow(Long id);

    List<TournamentView> findAllTournament();

    TournamentView create(Tournament req);

    boolean delete(Long id);

    boolean update(Tournament tournament, Tournament tournamentUpdate);
}
