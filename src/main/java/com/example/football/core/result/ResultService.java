package com.example.football.core.result;

import com.example.football.core.result.web.ResultConverter;
import com.example.football.core.result.web.ResultView;
import com.example.football.core.result.web.request.ResultBaseReq;
import com.example.football.core.team.Team;
import com.example.football.core.team.TeamRepo;
import com.example.football.core.tournament.TournamentRepo;
import com.example.football.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService{

    private final ResultRepo resultRepo;
    private final ResultConverter converter;
    private final TeamRepo teamRepo;
    private final TournamentRepo tournamentRepo;

    public ResultService(final ResultRepo resultRepo,
                         final ResultConverter converter,
                         final TeamRepo teamRepo, TournamentRepo tournamentRepo) {
        this.resultRepo = resultRepo;
        this.converter = converter;
        this.teamRepo = teamRepo;
        this.tournamentRepo = tournamentRepo;
    }

    public Result findResultOrThrow(Long id) {
       return resultRepo.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Result not found"));
    }

    public ResultView findResultViewOrThrow(Long id) {
        Result result = resultRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Result not found"));
        return converter.toView(result);
    }

    public Page<ResultView> findAllResult(Pageable pageable) {
        Page<Result> results = resultRepo.findAll(pageable);
        List<ResultView> views = converter.toViews(results);
        return new PageImpl<>(views, pageable, results.getTotalElements());
    }

    public ResultView create(ResultBaseReq req) {
        Result result = new Result();
        this.prepare(result,req);
        Result resultSave = resultRepo.save(result);
        return converter.toView(resultSave);
    }

    public boolean delete(Long id) {
        if(resultRepo.existsById(id)) {
            resultRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public ResultView update(Result result, ResultBaseReq req) {
        Result newResult = this.prepare(result,req);
        Result resultSave = resultRepo.save(newResult);
        return converter.toView(resultSave);
    }

    public Result prepare(Result result, ResultBaseReq resultBaseReq){
        result.setDraw(resultBaseReq.getDraw());
        result.setGoals(resultBaseReq.getGoals());
        result.setGoalsMissed(resultBaseReq.getGoalsMissed());
        result.setLoses(resultBaseReq.getLoses());
        result.setMissed(resultBaseReq.getMissed());
        result.setPlace(resultBaseReq.getPlace());
        result.setWins(resultBaseReq.getWins());
        result.setPoints(resultBaseReq.getPoints());
        result.setTeam(teamRepo.getOne(resultBaseReq.getTeamId()));
        result.setTournament(tournamentRepo.getOne(resultBaseReq.getTournamentId()));
        return result;
    }
}
