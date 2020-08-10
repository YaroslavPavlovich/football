package com.example.football.core.player.web;

import com.example.football.core.player.Player;
import com.example.football.core.player.PlayerService;
import com.example.football.core.tournament.Tournament;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(final PlayerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PlayerView getPlayer(@PathVariable Long id) {
        return service.findPlayerViewOrThrow(id);
    }

    @GetMapping
    @ResponseBody
    public List<PlayerView> getAllPlayer() {
        return service.findAllPlayer();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerView create(@RequestBody Player req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deletePlayer(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public boolean updatePlayer(@PathVariable(name = "id") Long id, @RequestBody Player player){
        Player playerUpdate = service.findPlayerOrThrow(id);
        return service.update(player, playerUpdate);
    }
}
