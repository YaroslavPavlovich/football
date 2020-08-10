package com.example.football.core.match;

import com.example.football.core.match.web.MatchView;

import java.util.List;

public interface MatchService {

    Match findMatchOrThrow(Long id);

    MatchView findMatchViewOrThrow(Long id);

    List<MatchView> findAllMatch();

    MatchView create(Match req);

    boolean delete(Long id);

    boolean update(Match match, Match matchUpdate);
}
