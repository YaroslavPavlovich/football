package com.example.football.core.coach.web.request;

import com.example.football.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CoachBaseReq extends BaseRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    private int age;

    private int expiriance;

    @NotNull
    private Long teamId;

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getSurname() {return  surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public int getAge(){return age;}

    public void setAge(int age) {this.age = age;}

    public int getExpiriance(){return expiriance;}

    public void setExpiriance(int expiriance) {this.expiriance = expiriance;}

    public Long getTeamId(){return teamId;}

    public void setTeam(Long teamId){this.teamId = teamId;}
}
