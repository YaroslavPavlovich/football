package com.example.football.core.player;

import com.example.football.core.team.Team;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fc_player")
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "surname_player")
    private String surname;

    @Column(name = "name_player")
    private String name;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;

    @Column(name = "age")
    private int age;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "fc_player_team",
            joinColumns = { @JoinColumn(name = "id_player") },
            inverseJoinColumns = { @JoinColumn(name = "id_team") })
    private Set<Team> teams = new HashSet<Team>();

    public long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname){this.surname = surname;}

    public int getHeight(){return height;}

    public void setHeight(int height){this.surname = surname;}

    public int getWeight(){return weight;}

    public void setWeight(int weight){this.weight = weight;}

    public int getAge(){return age;}

    public void setAge(int age){this.age = age;}

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
