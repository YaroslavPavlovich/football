package com.example.football.core.player;

import com.example.football.base.BaseRequest;
import com.example.football.core.player.web.PlayerConverter;
import com.example.football.core.player.web.PlayerView;
import com.example.football.core.player.web.request.PlayerBaseReq;
import com.example.football.core.team.Team;
import com.example.football.core.team.TeamRepo;
import com.example.football.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepo playerRepo;
    private final PlayerConverter converter;
    private final TeamRepo teamRepo;

    public PlayerService(final PlayerRepo playerRepo,
                         PlayerConverter converter, TeamRepo teamRepo) {
        this.playerRepo = playerRepo;
        this.converter = converter;
        this.teamRepo = teamRepo;
    }

    public Player findPlayerOrThrow(Long id) {
        return playerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found"));
    }

    public PlayerView findPlayerViewOrThrow(Long id) {
        Player player = playerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found"));
        return converter.toView(player);
    }

    public Page<PlayerView> findAllPlayer(Pageable pageable) {
        Page<Player> players = playerRepo.findAll(pageable);
        List<PlayerView> views = converter.toViews(players);
        return new PageImpl<>(views, pageable, players.getTotalElements());
    }

    public PlayerView create(PlayerBaseReq req) {
        Player player = new Player();
        this.prepare(player,req);
        Player playerSave = playerRepo.save(player);
        return converter.toView(playerSave);
    }

    public boolean delete(Long id) {
        if(playerRepo.existsById(id)) {
            playerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public PlayerView update(Player player, PlayerBaseReq req) {
        Player newPlayer = this.prepare(player,req);
        Player playerSave = playerRepo.save(newPlayer);
        return converter.toView(playerSave);
    }

    public Player prepare(Player player, PlayerBaseReq playerBaseReq){
        player.setName(playerBaseReq.getName());
        player.setSurname(playerBaseReq.getSurname());
        player.setWeight(playerBaseReq.getWeight());
        player.setHeight(playerBaseReq.getHeight());
        player.setAge(playerBaseReq.getAge());
        List<Team> teamList = teamRepo.findAllById(playerBaseReq.getTeams()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Team> teams = new HashSet<>(teamList);
        player.setTeams(teams);
        return player;
    }
}
