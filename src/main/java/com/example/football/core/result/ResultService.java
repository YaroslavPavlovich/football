package com.example.football.core.result;

import com.example.football.core.result.web.ResultView;

import java.util.List;

public interface ResultService {
    Result findResultOrThrow(Long id);

    ResultView findResultViewOrThrow(Long id);

    List<ResultView> findAllResult();

    ResultView create(Result req);

    boolean delete(Long id);

    boolean update(Result result, Result resultUpdate);
}
