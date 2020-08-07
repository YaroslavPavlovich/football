package com.example.football.core.coach;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepo coachRepo;

    public CoachServiceImpl(CoachRepo coachRepo) {
        this.coachRepo = coachRepo;
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
    public Coach create(Coach coach) {
        return coachRepo.save(coach);
    }

    @Override
    public List<Coach> findAllCoach() {
        return coachRepo.findAll();
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
