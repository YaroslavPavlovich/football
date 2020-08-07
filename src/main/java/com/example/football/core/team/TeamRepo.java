package com.example.football.core.team;

import com.example.football.core.tournament.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TeamRepo extends JpaRepository<Team, Long> {

    @Override
    Optional<Team> findById(@NonNull Long id);
}
