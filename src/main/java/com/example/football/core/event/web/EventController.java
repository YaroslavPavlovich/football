package com.example.football.core.event.web;

import com.example.football.core.event.Event;
import com.example.football.core.event.EventService;
import com.example.football.core.news.News;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService service;

    public EventController(final EventService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable Long id) {
        return service.findEventOrThrow(id);
    }

    @GetMapping
    @ResponseBody
    public List<Event> getAllEvent() {
        return service.findAllEvent();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Event create(@RequestBody Event req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEvent(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public boolean updateEvent(@PathVariable(name = "id") Long id, @RequestBody Event event){
        Event eventUpdate = service.findEventOrThrow(id);
        return service.update(event, eventUpdate);
    }
}
