package com.example.football.core.tournament.web.request;

import com.example.football.base.BaseRequest;
import com.example.football.core.tournament.Tournamentable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class TournamentBaseReq extends BaseRequest implements Tournamentable {

    @NotEmpty
    private String name;

    @NotEmpty
    private String country;

    private List<@Valid Id> teams;

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getCountry(){return country;}

    public void setCountry(String country){this.country = country;}

    public List<Id> getTeams(){return teams;}

    public void setTeams(List<Id> teams){this.teams = teams;}
}
