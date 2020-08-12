package com.example.football.core.tournament.web;

import com.example.football.core.tournament.Tournament;
import com.example.football.core.tournament.TournamentService;
import com.example.football.core.tournament.web.request.TournamentBaseReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public Page<TournamentView> getAllTournament(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllTournament(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TournamentView create(@RequestBody @Valid TournamentBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTournament(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public TournamentView updateTournament(@PathVariable(name = "id") Long id,
                                    @RequestBody @Valid TournamentBaseReq req){
        Tournament tournament = service.findTournamentOrThrow(id);
        return service.update(tournament, req);
    }
}
