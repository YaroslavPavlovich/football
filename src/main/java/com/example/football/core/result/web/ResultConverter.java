package com.example.football.core.result.web;

import com.example.football.core.result.Result;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultConverter {
    private final ResultToView toView;

    public ResultConverter(ResultToView toView) {
        this.toView = toView;
    }

    public ResultView toView(Result result) {
        return toView.convert(result);
    }

    public List<ResultView> toViews(Page<Result> resultPage) {
        List<ResultView> views = new ArrayList<ResultView>();
        List<Result> results = resultPage.getContent();
        results.forEach(result -> views.add(toView(result)));
        return views;
    }
}
