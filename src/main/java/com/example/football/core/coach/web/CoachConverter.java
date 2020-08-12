package com.example.football.core.coach.web;

import com.example.football.core.coach.Coach;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoachConverter {
    private final CoachToView toView;

    public CoachConverter(CoachToView toView) {
        this.toView = toView;
    }

    public CoachView toView(Coach coach) {
        return toView.convert(coach);
    }

    public List<CoachView> toViews(Page<Coach> coachesPage) {
        List<CoachView> views = new ArrayList<CoachView>();
        List<Coach> coaches = coachesPage.getContent();
        coaches.forEach(coach -> views.add(toView(coach)));
        return views;
    }
}
