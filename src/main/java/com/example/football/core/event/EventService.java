package com.example.football.core.event;

import com.example.football.core.news.News;

import java.util.List;

public interface EventService {
    Event findEventOrThrow(Long id);

    List<Event> findAllEvent();

    Event create(Event req);

    boolean delete(Long id);

    boolean update(Event event, Event eventUpdate);
}
