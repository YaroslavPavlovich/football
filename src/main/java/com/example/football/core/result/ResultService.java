package com.example.football.core.result;

import java.util.List;

public interface ResultService {
    Result findResultOrThrow(Long id);

    List<Result> findAllResult();

    Result create(Result req);

    boolean delete(Long id);

    boolean update(Result result, Result resultUpdate);
}
