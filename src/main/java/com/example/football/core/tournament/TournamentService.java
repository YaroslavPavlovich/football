package com.example.football.core.tournament;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TournamentService {

    @Autowired
    private final TournamentRepo tournamentRepo;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public TournamentService(final TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    public Tournament findTournamentOrThrow(Long id) {
        return tournamentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
    }
}
