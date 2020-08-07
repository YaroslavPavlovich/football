package com.example.football.core.result;

import com.example.football.core.tournament.TournamentRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService{

    private final ResultRepo resultRepo;

    public ResultServiceImpl(final ResultRepo resultRepo) {
        this.resultRepo = resultRepo;
    }

    @Override
    public Result findResultOrThrow(Long id) {
        if(resultRepo.existsById(id)) {
            return resultRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Result not found"));
        }
        return null;
    }

    @Override
    public List<Result> findAllResult() {
        return resultRepo.findAll();
    }

    @Override
    public Result create(Result result) {
        return resultRepo.save(result);
    }

    @Override
    public boolean delete(Long id) {
        if(resultRepo.existsById(id)) {
            resultRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Result result, Result resultUpdate) {
        resultUpdate.setDraw(result.getDraw());
        resultUpdate.setGoals(result.getGoals());
        resultUpdate.setGoalsMissed(result.getGoalsMissed());
        resultUpdate.setLoses(result.getLoses());
        resultUpdate.setMissed(result.getMissed());
        resultUpdate.setPlace(result.getPlace());
        resultUpdate.setWins(result.getWins());
        resultUpdate.setPoints(result.getPoints());
        if (result.getTeam()!= null) {
            resultUpdate.setTeam(result.getTeam());
        }
        if (result.getTournament()!= null) {
            resultUpdate.setTournament(result.getTournament());
        }
        resultRepo.save(resultUpdate);
        return true;
    }
}
