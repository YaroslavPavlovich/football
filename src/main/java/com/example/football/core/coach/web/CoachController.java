package com.example.football.core.coach.web;

import com.example.football.core.coach.Coach;
import com.example.football.core.coach.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coach")
public class CoachController {
    private final CoachService service;

    public CoachController(CoachService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Coach getCoach(@PathVariable Long id) {
        return service.findCoachOrThrow(id);
    }

    @GetMapping
    @ResponseBody
    public List<Coach> getAllCoach() {
        return service.findAllCoach();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Coach create(@RequestBody Coach req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCoach(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public boolean updateCoach(@PathVariable(name = "id") Long id, @RequestBody Coach coach) {
        Coach coachUpdate = service.findCoachOrThrow(id);
        return service.update(coach, coachUpdate);
    }
}
