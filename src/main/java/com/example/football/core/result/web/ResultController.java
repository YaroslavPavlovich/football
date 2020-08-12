package com.example.football.core.result.web;

import com.example.football.core.result.Result;
import com.example.football.core.result.ResultService;
import com.example.football.core.result.web.request.ResultBaseReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {

    private final ResultService service;

    public ResultController(final ResultService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResultView getResult(@PathVariable Long id) {
        return service.findResultViewOrThrow(id);
    }

    @GetMapping
    @ResponseBody
    public Page<ResultView> getAllResult(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return service.findAllResult(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResultView create(@RequestBody @Valid ResultBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    public boolean deleteResult(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public ResultView updateResult(@PathVariable(name = "id") Long id,
                                @RequestBody @Valid ResultBaseReq req){
        Result result = service.findResultOrThrow(id);
        return service.update(result, req);
    }
}
