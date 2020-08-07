package com.example.football.core.news;

import com.example.football.core.team.Team;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fc_news")
public class News {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_news")
    private String name;

    @Column(name = "content_news")
    private String content;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "fc_team_news",
            joinColumns = { @JoinColumn(name = "id_news") },
            inverseJoinColumns = { @JoinColumn(name = "id_team") })
    private Set<Team> teams = new HashSet<Team>();

    public long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getContent(){return content;}

    public void setContent(String content){this.content = content;}

    public Set<Team> getTeams(){return teams;}

    public void setTeams(Set<Team> teams) {this.teams = teams;}
}
