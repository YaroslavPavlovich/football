package com.example.football.core.coach;

import com.example.football.core.coach.web.CoachConverter;
import com.example.football.core.coach.web.CoachView;
import com.example.football.core.coach.web.request.CoachBaseReq;
import com.example.football.core.team.TeamRepo;
import com.example.football.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {

    private final CoachRepo coachRepo;
    private final CoachConverter converter;
    private final TeamRepo teamRepo;

    public CoachService(CoachRepo coachRepo,
                        CoachConverter converter,
                        TeamRepo teamRepo) {
        this.coachRepo = coachRepo;
        this.converter = converter;
        this.teamRepo = teamRepo;
    }

    public Coach findCoachOrThrow(Long id) {
        return coachRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coach not found"));
    }

    public CoachView findCoachViewOrThrow(Long id) {
        Coach coach = coachRepo.findById(id)
                   .orElseThrow(() -> new EntityNotFoundException("Coach not found"));
        return converter.toView(coach);
    }

    public CoachView create(CoachBaseReq req) {
        Coach coach = new Coach();
        this.prepare(coach,req);
        Coach coachSave = coachRepo.save(coach);
        return converter.toView(coachSave);
    }

    public Page<CoachView> findAllCoach(Pageable pageable) {
        Page<Coach> coaches = coachRepo.findAll(pageable);
        List<CoachView> views = converter.toViews(coaches);
        return new PageImpl<>(views, pageable, coaches.getTotalElements());
    }

    public boolean delete(Long id) {
        if(coachRepo.existsById(id)) {
            coachRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public CoachView update(Coach coach, CoachBaseReq req) {
        Coach newCoach = this.prepare(coach,req);
        Coach coachSave = coachRepo.save(newCoach);
        return converter.toView(coachSave);
    }

    private Coach prepare(Coach coach, CoachBaseReq req) {
        coach.setName(req.getName());
        coach.setSurname(req.getSurname());
        coach.setExpiriance(req.getExpiriance());
        coach.setAge(req.getAge());
        coach.setTeam(teamRepo.getOne(req.getTeamId()));
        return coach;
    }
}
