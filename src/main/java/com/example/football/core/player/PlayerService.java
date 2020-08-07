package com.example.football.core.player;

import java.util.List;

public interface PlayerService {
    Player findPlayerOrThrow(Long id);

    List<Player> findAllPlayer();

    Player create(Player req);

    boolean delete(Long id);

    boolean update(Player player, Player playerUpdate);
}
