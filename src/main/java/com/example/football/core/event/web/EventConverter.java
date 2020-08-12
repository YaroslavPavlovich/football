package com.example.football.core.event.web;

import com.example.football.core.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventConverter {
    private final EventToView toView;

    public EventConverter(EventToView toView) {
        this.toView = toView;
    }

    public EventView toView(Event event) {
        return toView.convert(event);
    }

    public List<EventView> toViews(Page<Event> eventsPage) {
        List<EventView> views = new ArrayList<EventView>();
        List<Event> events = eventsPage.getContent();
        events.forEach(event -> views.add(toView(event)));
        return views;
    }
}

