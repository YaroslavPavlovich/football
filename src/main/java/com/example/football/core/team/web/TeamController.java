package com.example.football.core.team.web;

import com.example.football.core.team.Team;
import com.example.football.core.team.TeamService;
import com.example.football.core.team.web.request.TeamBaseReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

    @RestController
    @RequestMapping("/team")
    public class TeamController {
        private final TeamService service;

        public TeamController(TeamService service) {
            this.service = service;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public TeamView getTeam(@PathVariable Long id) {
            return service.findTeamViewOrThrow(id);
        }

        @GetMapping
        @ResponseBody
        public Page<TeamView> getAllTeam(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllTeam(pageable);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public TeamView create(@RequestBody @Valid TeamBaseReq req) {
            return service.create(req);
        }

        @DeleteMapping("/{id}")
        public boolean deleteTeam(@PathVariable Long id){
            return service.delete(id);
        }

        @PutMapping("/{id}")
        public TeamView updateTeam(@PathVariable(name = "id") Long id,
                                  @RequestBody @Valid TeamBaseReq req){
            Team team = service.findTeamOrThrow(id);
            return service.update(team, req);
        }
    }

