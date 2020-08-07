package com.example.football.core.match.web;

import com.example.football.core.match.Match;
import com.example.football.core.match.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {
    private final MatchService service;

    public MatchController(MatchService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Match getMatch(@PathVariable Long id) {
        return service.findMatchOrThrow(id);
    }

    @GetMapping
    @ResponseBody
    public List<Match> getAllMatch() {
        return service.findAllMatch();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Match create(@RequestBody Match req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMatch(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public boolean updateTournament(@PathVariable(name = "id") Long id, @RequestBody Match match) {
        Match matchUpdate = service.findMatchOrThrow(id);
        return service.update(match, matchUpdate);
    }
}
