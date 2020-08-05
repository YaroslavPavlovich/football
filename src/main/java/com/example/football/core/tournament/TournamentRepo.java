package com.example.football.core.tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Long>, JpaSpecificationExecutor<Tournament> {

    @Override
    Optional<Tournament> findById(@NonNull Long id);
}
