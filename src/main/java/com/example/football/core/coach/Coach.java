package com.example.football.core.coach;

import com.example.football.core.team.Team;

import javax.persistence.*;

@Entity
@Table(name = "fc_coach")
public class Coach {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_coach")
    private String name;

    @Column(name = "surname_coach")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "experiance")
    private int expiriance;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_team")
    private Team team;

    public long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getSurname() {return  surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public int getAge(){return age;}

    public void setAge(int age) {this.age = age;}

    public int getExpiriance(){return expiriance;}

    public void setExpiriance(int expiriance) {this.expiriance = expiriance;}

    public Team getTeam(){return team;}

    public void setTeam(Team team){this.team = team;}
}
