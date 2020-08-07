package com.example.football.core.news;

import com.example.football.core.news.NewsRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepo;

    public NewsServiceImpl(final NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
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
    public List<News> findAllNews() {
        return newsRepo.findAll();
    }

    @Override
    public News create(News news) {
        return newsRepo.save(news);
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
