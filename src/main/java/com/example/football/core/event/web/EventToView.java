package com.example.football.core.event.web;

import com.example.football.core.event.Event;
import com.example.football.core.player.Player;
import com.example.football.core.player.web.PlayerConverter;
import com.example.football.core.player.web.PlayerView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class EventToView implements Converter<Event,EventView> {
    private final PlayerConverter converter;

    public EventToView(PlayerConverter converter) {
        this.converter = converter;
    }

    @Override
    public EventView convert(@NonNull Event event) {
        EventView view = new EventView();
        view.setId(event.getId());
        view.setName(event.getName());
        view.setContent(event.getContent());
        Set<PlayerView> views = new HashSet<>();
        Set<Player> players= event.getPlayers();
        players.forEach(player -> views.add(converter.toView(player)));
        view.setPlayers(views);
        return view;
    }
}
