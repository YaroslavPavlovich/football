package com.example.football.core.tournament;

import com.example.football.core.tournament.web.request.TournamentCreateReq;

import java.util.List;

public interface TournamentService {
    Tournament findTournamentOrThrow(Long id);

    List<Tournament> findAllTournament();

    Tournament create(Tournament req);

    boolean delete(Long id);

    boolean update(Tournament tournament, Tournament tournamentUpdate);
}
