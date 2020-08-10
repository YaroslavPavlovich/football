package com.example.football.core.news.web;

import com.example.football.core.news.News;
import com.example.football.core.news.NewsService;
import com.example.football.core.tournament.Tournament;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService service;

    public NewsController(final NewsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public NewsView getNews(@PathVariable Long id) {
        return service.findNewsViewOrThrow(id);
    }

    @GetMapping
    @ResponseBody
    public List<NewsView> getAllNews() {
        return service.findAllNews();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public NewsView create(@RequestBody News req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deleteNews(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public boolean updateNews(@PathVariable(name = "id") Long id, @RequestBody News news){
        News newsUpdate = service.findNewsOrThrow(id);
        return service.update(news, newsUpdate);
    }
}
