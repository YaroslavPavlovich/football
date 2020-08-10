package com.example.football.core.coach;

import com.example.football.core.coach.web.CoachConverter;
import com.example.football.core.coach.web.CoachView;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepo coachRepo;
    private final CoachConverter converter;

    public CoachServiceImpl(CoachRepo coachRepo,
                            CoachConverter converter) {
        this.coachRepo = coachRepo;
        this.converter = converter;
    }

    @Override
    public Coach findCoachOrThrow(Long id) {
        if(coachRepo.existsById(id)) {
            return coachRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Match not found"));
        }
        return null;
    }

    @Override
    public CoachView findCoachViewOrThrow(Long id) {
        if(coachRepo.existsById(id)) {
            Coach coach = coachRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Match not found"));
            return converter.toView(coach);
        }
        return null;
    }

    @Override
    public CoachView create(Coach coach) {
        Coach coachSave = coachRepo.save(coach);
        return converter.toView(coachSave);
    }

    @Override
    public List<CoachView> findAllCoach() {
        List<Coach> coaches = coachRepo.findAll();
        return converter.toViews(coaches);
    }

    @Override
    public boolean delete(Long id) {
        if(coachRepo.existsById(id)) {
            coachRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Coach coach, Coach coachUpdate) {
        coachUpdate.setName(coach.getName());
        coachUpdate.setSurname(coach.getSurname());
        coachUpdate.setAge(coachUpdate.getAge());
        coachUpdate.setExpiriance(coachUpdate.getExpiriance());
        if (coach.getTeam() != null) {
            coachUpdate.setTeam(coach.getTeam());
        }
        coachRepo.save(coachUpdate);
        return true;
    }
}
