package com.example.football.core.coach.web;

import com.example.football.core.coach.Coach;
import com.example.football.core.team.Team;
import com.example.football.core.team.web.TeamConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CoachToView implements Converter<Coach,CoachView> {
    private final TeamConverter converter;

    public CoachToView(TeamConverter converter) {
        this.converter = converter;
    }

    @Override
    public CoachView convert(@NonNull Coach coach) {
        CoachView view = new CoachView();
        view.setId(coach.getId());
        view.setName(coach.getName());
        view.setSurname(coach.getSurname());
        view.setAge(coach.getAge());
        view.setExpiriance(coach.getExpiriance());
        Team team = coach.getTeam();
        view.setTeam(converter.toView(team));
        return view;
    }
}
