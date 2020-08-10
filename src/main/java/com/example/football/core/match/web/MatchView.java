package com.example.football.core.match.web;


import com.example.football.core.team.web.TeamView;
import com.example.football.core.tournament.web.TournamentView;

import java.util.Date;

public class MatchView {
    private long id;

    private TeamView teamOwner;

    private TeamView teamGuest;

    private int scoreOwners;

    private int scoreGuests;

    private Date matchDate;

    private TournamentView tournament;

    public long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public TeamView getOwner(){return teamOwner;}

    public void setOwner(TeamView teamOwner){this.teamOwner = teamOwner;}

    public TeamView getGuest(){return teamGuest;}

    public void setGuest(TeamView teamGuest){this.teamGuest = teamGuest;}

    public int getScoreOwners(){return scoreOwners;}

    public void setScoreOwners(int scoreOwners){this.scoreOwners = scoreOwners;}

    public int getScoreGuests(){return scoreGuests;}

    public void setScoreGuests(int scoreGuests){this.scoreGuests = scoreGuests;}

    public Date getMatchDate(){return matchDate;}

    public void setMatchDate(Date matchDate){this.matchDate = matchDate;}

    public TournamentView getTournament(){return tournament;}

    public void setTournament(TournamentView tournament){this.tournament = tournament;}
}
