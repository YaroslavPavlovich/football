package com.example.football.core.news;

import com.example.football.base.BaseRequest;
import com.example.football.core.news.web.NewsConverter;
import com.example.football.core.news.web.NewsView;
import com.example.football.core.news.web.request.NewsBaseReq;
import com.example.football.core.team.Team;
import com.example.football.core.team.TeamRepo;
import com.example.football.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NewsService{

    private final NewsRepo newsRepo;
    private final NewsConverter converter;
    private final TeamRepo teamRepo;

    public NewsService(final NewsRepo newsRepo, NewsConverter converter, TeamRepo teamRepo) {
        this.newsRepo = newsRepo;
        this.converter = converter;
        this.teamRepo = teamRepo;
    }

    public News findNewsOrThrow(Long id) {
        return newsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found"));
    }

    public NewsView findNewsViewOrThrow(Long id) {
        News news = newsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found"));
        return converter.toView(news);
    }

    public Page<NewsView> findAllNews(Pageable pageable) {
        Page<News> news = newsRepo.findAll(pageable);
        List<NewsView> views = converter.toViews(news);
        return new PageImpl<>(views, pageable, news.getTotalElements());
    }

    public NewsView create(NewsBaseReq req) {
        News news = new News();
        this.prepare(news,req);
        News newsSave = newsRepo.save(news);
        return converter.toView(newsSave);
    }

    public boolean delete(Long id) {
        if(newsRepo.existsById(id)) {
            newsRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public NewsView update(News news, NewsBaseReq req) {
        News newNews = this.prepare(news,req);
        News newsSave = newsRepo.save(newNews);
        return converter.toView(newsSave);
    }


    private News prepare(News news, NewsBaseReq req) {
        news.setName(req.getName());
        news.setContent(req.getContent());
        List<Team> teamList = teamRepo.findAllById(req.getTeams()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Team> teams = new HashSet<>(teamList);
        news.setTeams(teams);
        return news;
    }
}
