package com.example.football.core.player;

import com.example.football.core.player.web.PlayerConverter;
import com.example.football.core.player.web.PlayerView;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepo playerRepo;
    private final PlayerConverter converter;

    public PlayerServiceImpl(final PlayerRepo playerRepo,
                             PlayerConverter converter) {
        this.playerRepo = playerRepo;
        this.converter = converter;
    }

    @Override
    public Player findPlayerOrThrow(Long id) {
        if(playerRepo.existsById(id)) {
            return playerRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Player not found"));
        }
        return null;
    }

    @Override
    public PlayerView findPlayerViewOrThrow(Long id) {
        if(playerRepo.existsById(id)) {
            Player player = playerRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Player not found"));
            return converter.toView(player);
        }
        return null;
    }

    @Override
    public List<PlayerView> findAllPlayer() {
        List<Player> players = playerRepo.findAll();
        return converter.toViews(players);
    }

    @Override
    public PlayerView create(Player player) {
        Player playerSave = playerRepo.save(player);
        return converter.toView(playerSave);
    }

    @Override
    public boolean delete(Long id) {
        if(playerRepo.existsById(id)) {
            playerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Player player, Player playerUpdate) {
        playerUpdate.setName(player.getName());
        playerUpdate.setSurname(player.getSurname());
        playerUpdate.setAge(player.getAge());
        playerUpdate.setHeight(player.getHeight());
        playerUpdate.setWeight(player.getWeight());
        if (player.getTeams().size()!= 0) {
            playerUpdate.setTeams(player.getTeams());
        }
        playerRepo.save(playerUpdate);
        return true;
    }
}
