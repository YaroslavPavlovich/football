package com.example.football.core.coach;

import com.example.football.core.coach.web.CoachView;

import java.util.List;

public interface CoachService {
    Coach findCoachOrThrow(Long id);

    CoachView findCoachViewOrThrow(Long id);

    CoachView create(Coach req);

    List<CoachView> findAllCoach();

    boolean delete(Long id);

    boolean update(Coach coach, Coach coachUpdate);
}
