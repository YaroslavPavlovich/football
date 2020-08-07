package com.example.football.core.event;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;

    public EventServiceImpl(final EventRepo eventRepo) {
        this.eventRepo = eventRepo;
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
    public List<Event> findAllEvent() {
        return eventRepo.findAll();
    }

    @Override
    public Event create(Event event) {
        return eventRepo.save(event);
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
