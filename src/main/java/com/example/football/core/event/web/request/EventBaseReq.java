package com.example.football.core.event.web.request;

import com.example.football.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class EventBaseReq extends BaseRequest{
    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotEmpty
    private List<@Valid Id> players;

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getContent(){return content;}

    public void setContent(String content){this.content = content;}

    public List<BaseRequest.Id> getPlayers(){return players;}

    public void setPlayers(List<BaseRequest.Id> players) {this.players = players;}
}
