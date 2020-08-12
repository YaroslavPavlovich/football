package com.example.football.core.player.web;

import com.example.football.core.player.Player;
import com.example.football.core.player.PlayerService;
import com.example.football.core.player.web.request.PlayerBaseReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Page<PlayerView> getAllPlayer(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllPlayer(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PlayerView create(@RequestBody @Valid PlayerBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deletePlayer(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public PlayerView updatePlayer(@PathVariable(name = "id") Long id,
                                @RequestBody @Valid PlayerBaseReq req){
        Player player = service.findPlayerOrThrow(id);
        return service.update(player, req);
    }
}
