package com.example.football.core.news;

import com.example.football.core.news.web.NewsView;

import java.util.List;

public interface NewsService {
    News findNewsOrThrow(Long id);

    NewsView findNewsViewOrThrow(Long id);

    List<NewsView> findAllNews();

    NewsView create(News req);

    boolean delete(Long id);

    boolean update(News news, News newsUpdate);
}
