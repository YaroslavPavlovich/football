package com.example.football.core.player;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepo playerRepo;

    public PlayerServiceImpl(final PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
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
    public List<Player> findAllPlayer() {
        return playerRepo.findAll();
    }

    @Override
    public Player create(Player player) {
        return playerRepo.save(player);
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
