package com.example.football.core.event;

import com.example.football.core.event.web.EventView;
import com.example.football.core.news.News;

import java.util.List;

public interface EventService {
    Event findEventOrThrow(Long id);

    EventView findEventViewOrThrow(Long id);

    List<EventView> findAllEvent();

    EventView create(Event req);

    boolean delete(Long id);

    boolean update(Event event, Event eventUpdate);
}
