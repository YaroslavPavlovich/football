package com.example.football.core.tournament.web;

import com.example.football.core.tournament.Tournament;
import com.example.football.core.tournament.TournamentService;
import com.example.football.core.tournament.web.request.TournamentCreateReq;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentService service;

    public TournamentController(final TournamentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TournamentView getTournament(@PathVariable Long id) {
        return service.findTournamentViewOrThrow(id);
    }

    @GetMapping
    @ResponseBody
    public List<TournamentView> getAllTournament() {
        return service.findAllTournament();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TournamentView create(@RequestBody Tournament req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTournament(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public boolean updateTournament(@PathVariable(name = "id") Long id, @RequestBody Tournament tournament){
        Tournament tournamentUpdate = service.findTournamentOrThrow(id);
        return service.update(tournament, tournamentUpdate);
    }
}
