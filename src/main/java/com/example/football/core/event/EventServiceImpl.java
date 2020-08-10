package com.example.football.core.event;

import com.example.football.core.event.web.EventConverter;
import com.example.football.core.event.web.EventView;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;
    private final EventConverter converter;

    public EventServiceImpl(final EventRepo eventRepo,
                            EventConverter converter) {
        this.eventRepo = eventRepo;
        this.converter = converter;
    }


    @Override
    public Event findEventOrThrow(Long id) {
        if(eventRepo.existsById(id)) {
            return eventRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Events not found"));
        }
        return null;
    }

    @Override
    public EventView findEventViewOrThrow(Long id) {
        if(eventRepo.existsById(id)) {
            Event event = eventRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Events not found"));
            return converter.toView(event);
        }
        return null;
    }

    @Override
    public List<EventView> findAllEvent() {
        List<Event> events = eventRepo.findAll();
        return converter.toViews(events);
    }

    @Override
    public EventView create(Event event) {
        Event eventSave = eventRepo.save(event);
        return converter.toView(eventSave);
    }

    @Override
    public boolean delete(Long id) {
        if(eventRepo.existsById(id)) {
            eventRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Event event, Event eventUpdate) {
        eventUpdate.setName(event.getName());
        eventUpdate.setContent(event.getContent());
        if (event.getPlayers().size()!= 0) {
            eventUpdate.setPlayers(event.getPlayers());
        }
        eventRepo.save(eventUpdate);
        return true;
    }
}
