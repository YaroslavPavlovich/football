package com.example.football.core.team.web;

import com.example.football.core.team.Team;
import com.example.football.core.team.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        public Team getTeam(@PathVariable Long id) {
            return service.findTeamOrThrow(id);
        }

        @GetMapping
        @ResponseBody
        public List<Team> getAllTeam() {
            return service.findAllTeam();
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public Team create(@RequestBody Team req) {
            return service.create(req);
        }

        @DeleteMapping("/{id}")
        public boolean deleteTeam(@PathVariable Long id){
            return service.delete(id);
        }

        @PutMapping("/{id}")
        public boolean updateTeam(@PathVariable(name = "id") Long id, @RequestBody Team team){
            return service.update(team, id);
        }
    }
