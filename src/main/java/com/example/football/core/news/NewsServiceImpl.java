package com.example.football.core.news;

import com.example.football.core.news.web.NewsConverter;
import com.example.football.core.news.web.NewsView;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepo;
    private final NewsConverter converter;

    public NewsServiceImpl(final NewsRepo newsRepo, NewsConverter converter) {
        this.newsRepo = newsRepo;
        this.converter = converter;
    }

    @Override
    public News findNewsOrThrow(Long id) {
        if(newsRepo.existsById(id)) {
            return newsRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("News not found"));
        }
        return null;
    }

    @Override
    public NewsView findNewsViewOrThrow(Long id) {
        if(newsRepo.existsById(id)) {
            News news = newsRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("News not found"));
            return converter.toView(news);
        }
        return null;
    }

    @Override
    public List<NewsView> findAllNews() {
        List<News> news = newsRepo.findAll();
        return converter.toViews(news);
    }

    @Override
    public NewsView create(News news) {
        News newsSave = newsRepo.save(news);
        return converter.toView(newsSave);
    }

    @Override
    public boolean delete(Long id) {
        if(newsRepo.existsById(id)) {
            newsRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(News news, News newsUpdate) {
        newsUpdate.setName(news.getName());
        newsUpdate.setContent(news.getContent());
        if (news.getTeams().size()!= 0) {
            newsUpdate.setTeams(news.getTeams());
        }
        newsRepo.save(newsUpdate);
        return true;
    }
}
