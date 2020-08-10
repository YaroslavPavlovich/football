package com.example.football.core.result;

import com.example.football.core.result.web.ResultConverter;
import com.example.football.core.result.web.ResultView;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService{

    private final ResultRepo resultRepo;
    private final ResultConverter converter;

    public ResultServiceImpl(final ResultRepo resultRepo,
                             ResultConverter converter) {
        this.resultRepo = resultRepo;
        this.converter = converter;
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
    public ResultView findResultViewOrThrow(Long id) {
        if(resultRepo.existsById(id)) {
            Result result = resultRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Result not found"));
            return converter.toView(result);
        }
        return null;
    }

    @Override
    public List<ResultView> findAllResult() {
        List<Result> results = resultRepo.findAll();
        return converter.toViews(results);
    }

    @Override
    public ResultView create(Result result) {
        Result resultSave = resultRepo.save(result);
        return converter.toView(resultSave);
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
