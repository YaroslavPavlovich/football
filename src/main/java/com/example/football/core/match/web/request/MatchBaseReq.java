package com.example.football.core.match.web.request;

import com.example.football.base.BaseRequest;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class MatchBaseReq extends BaseRequest {

    @NotNull
    private Long teamOwnerId;

    @NotNull
    private Long teamGuestId;

    private int scoreOwners;

    private int scoreGuests;

    @NotNull
    private Date matchDate;

    @NotNull
    private Long tournamentId;

    public Long getOwnerId(){return teamOwnerId;}

    public void setOwnerId(Long teamOwnerId){this.teamOwnerId = teamOwnerId;}

    public Long getGuestId(){return teamGuestId;}

    public void setGuestId(Long teamGuestId){this.teamGuestId = teamGuestId;}

    public int getScoreOwners(){return scoreOwners;}

    public void setScoreOwners(int scoreOwners){this.scoreOwners = scoreOwners;}

    public int getScoreGuests(){return scoreGuests;}

    public void setScoreGuests(int scoreGuests){this.scoreGuests = scoreGuests;}

    public Date getMatchDate(){return matchDate;}

    public void setMatchDate(Date matchDate){this.matchDate = matchDate;}

    public Long getTournamentId(){return tournamentId;}

    public void setTournamentId(Long tournamentId){this.tournamentId = tournamentId;}
}
