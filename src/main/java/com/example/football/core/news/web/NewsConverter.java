package com.example.football.core.news.web;

import com.example.football.core.news.News;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsConverter {
    private final NewsToView toView;

    public NewsConverter(NewsToView toView) {
        this.toView = toView;
    }

    public NewsView toView(News news) {
        return toView.convert(news);
    }

    public List<NewsView> toViews(Page<News> newsPage) {
        List<NewsView> views = new ArrayList<NewsView>();
        List<News> news = newsPage.getContent();
        news.forEach(news1 -> views.add(toView(news1)));
        return views;
    }
}
