package com.example.football.core.event.web;

import com.example.football.core.event.Event;
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

    public List<EventView> toViews(List<Event> events) {
        List<EventView> views = new ArrayList<EventView>();
        events.forEach(event -> views.add(toView(event)));
        return views;
    }
}

