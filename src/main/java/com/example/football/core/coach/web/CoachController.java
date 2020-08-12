package com.example.football.core.coach.web;

import com.example.football.core.coach.Coach;
import com.example.football.core.coach.CoachService;
import com.example.football.core.coach.web.request.CoachBaseReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public CoachView getCoach(@PathVariable Long id) {
        return service.findCoachViewOrThrow(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CoachView> getAllCoach(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllCoach(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CoachView create(@RequestBody @Valid CoachBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCoach(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public CoachView updateCoach(@PathVariable(name = "id") Long id,
                               @RequestBody @Valid CoachBaseReq req) {
        Coach coach = service.findCoachOrThrow(id);
        return service.update(coach, req);
    }
}
