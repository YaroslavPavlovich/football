package com.example.football.core.coach;

import java.util.List;

public interface CoachService {
    Coach findCoachOrThrow(Long id);

    Coach create(Coach req);

    List<Coach> findAllCoach();

    boolean delete(Long id);

    boolean update(Coach coach, Coach coachUpdate);
}
