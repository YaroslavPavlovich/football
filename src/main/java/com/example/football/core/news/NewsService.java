package com.example.football.core.news;

import java.util.List;

public interface NewsService {
    News findNewsOrThrow(Long id);

    List<News> findAllNews();

    News create(News req);

    boolean delete(Long id);

    boolean update(News news, News newsUpdate);
}
