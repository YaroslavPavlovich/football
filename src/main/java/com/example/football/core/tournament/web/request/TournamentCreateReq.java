package com.example.football.core.tournament.web.request;

import javax.validation.constraints.NotEmpty;

public class TournamentCreateReq {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String country;

    public long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getCountry(){return country;}

    public void setCountry(String country){this.country = country;}
}
