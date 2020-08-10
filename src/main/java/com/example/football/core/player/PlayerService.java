package com.example.football.core.player;

import com.example.football.core.player.web.PlayerView;

import java.util.List;

public interface PlayerService {
    Player findPlayerOrThrow(Long id);

    PlayerView findPlayerViewOrThrow(Long id);

    List<PlayerView> findAllPlayer();

    PlayerView create(Player req);

    boolean delete(Long id);

    boolean update(Player player, Player playerUpdate);
}
