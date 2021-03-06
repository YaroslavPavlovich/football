package com.example.football.core.tournament;

import com.example.football.core.team.Team;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fc_tournament")
public class Tournament {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tournament_name")
    private String name;

    @Column(name = "country")
    private String country;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "fc_tournament_team",
            joinColumns = { @JoinColumn(name = "id_tournament") },
            inverseJoinColumns = { @JoinColumn(name = "id_team") })
    private Set<Team> teams = new HashSet<Team>();

    public long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getCountry(){return country;}

    public void setCountry(String country){this.country = country;}

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
