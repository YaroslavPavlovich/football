package com.example.football.core.tournament;

import javax.persistence.*;

@Entity
@Table(name = "fc_tournament")
public class Tournament {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tournament_name")
    private String name;

    @Column(name = "country ")
    private String country;

    public long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getCountry(){return country;}

    public void setCountry(String country){this.country = country;}
}
