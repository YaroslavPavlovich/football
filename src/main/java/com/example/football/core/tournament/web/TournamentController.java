package com.example.football.core.tournament.web;

import com.example.football.core.tournament.Tournament;
import com.example.football.core.tournament.TournamentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentService service;

    public TournamentController(final TournamentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Tournament getTournamentDetails(@PathVariable Long id) {
        return service.findTournamentOrThrow(id);
    }
}
