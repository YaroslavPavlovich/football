package com.example.football.core.event;

import com.example.football.base.BaseRequest;
import com.example.football.core.event.web.EventConverter;
import com.example.football.core.event.web.EventView;
import com.example.football.core.event.web.request.EventBaseReq;
import com.example.football.core.player.Player;
import com.example.football.core.player.PlayerRepo;
import com.example.football.core.team.Team;
import com.example.football.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepo eventRepo;
    private final EventConverter converter;
    private final PlayerRepo playerRepo;

    public EventService(final EventRepo eventRepo,
                        EventConverter converter,
                        PlayerRepo playerRepo) {
        this.eventRepo = eventRepo;
        this.converter = converter;
        this.playerRepo = playerRepo;
    }

    public Event findEventOrThrow(Long id) {
        return eventRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Events not found"));
    }

    public EventView findEventViewOrThrow(Long id) {
        Event event = eventRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Events not found"));
        return converter.toView(event);
    }

    public Page<EventView> findAllEvent(Pageable pageable) {
        Page<Event> events = eventRepo.findAll(pageable);
        List<EventView> views = converter.toViews(events);
        return new PageImpl<>(views, pageable, events.getTotalElements());
    }

    public EventView create(EventBaseReq req) {
        Event event = new Event();
        this.prepare(event,req);
        Event eventSave = eventRepo.save(event);
        return converter.toView(eventSave);
    }

    public boolean delete(Long id) {
        if(eventRepo.existsById(id)) {
            eventRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public EventView update(Event event, EventBaseReq req) {
        Event newEvent = this.prepare(event,req);
        Event eventSave = eventRepo.save(newEvent);
        return converter.toView(eventSave);
    }

    private Event prepare(Event event, EventBaseReq req) {
        event.setName(req.getName());
        event.setContent(req.getContent());
        List<Player> playerList = playerRepo.findAllById(req.getPlayers()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Player> players = new HashSet<>(playerList);
        event.setPlayers(players);
        return event;
    }
}
