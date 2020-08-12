package com.example.football.core.player.web;

import com.example.football.core.player.Player;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerConverter {
    private final PlayerToView toView;

    public PlayerConverter(PlayerToView toView) {
        this.toView = toView;
    }

    public PlayerView toView(Player player) {
        return toView.convert(player);
    }

    public List<PlayerView> toViews(Page<Player> playerPage) {
        List<PlayerView> views = new ArrayList<PlayerView>();
        List<Player> players = playerPage.getContent();
        players.forEach(player -> views.add(toView(player)));
        return views;
    }
}
