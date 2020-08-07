package com.example.football.core.match;

import java.util.List;

public interface MatchService {

    Match findMatchOrThrow(Long id);

    List<Match> findAllMatch();

    Match create(Match req);

    boolean delete(Long id);

    boolean update(Match match, Match matchUpdate);
}
