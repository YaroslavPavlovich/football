package com.example.football.core.player.web;

import com.example.football.core.player.Player;
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

    public List<PlayerView> toViews(List<Player> tournaments) {
        List<PlayerView> views = new ArrayList<PlayerView>();
        tournaments.forEach(player -> views.add(toView(player)));
        return views;
    }
}
