package com.example.football.core.result.web;

import com.example.football.core.result.Result;
import com.example.football.core.result.ResultService;
import com.example.football.core.tournament.Tournament;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {

    private final ResultService service;

    public ResultController(final ResultService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResultView getResult(@PathVariable Long id) {
        return service.findResultViewOrThrow(id);
    }

    @GetMapping
    @ResponseBody
    public List<ResultView> getAllTournament() {
        return service.findAllResult();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResultView create(@RequestBody Result req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deleteResult(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public boolean updateResult(@PathVariable(name = "id") Long id, @RequestBody Result result){
        Result resultUpdate = service.findResultOrThrow(id);
        return service.update(result, resultUpdate);
    }
}
