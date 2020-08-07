package com.example.football.core.event;

import com.example.football.core.player.Player;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fc_event")
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_event")
    private String name;

    @Column(name = "content_event")
    private String content;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "fc_player_event",
            joinColumns = { @JoinColumn(name = "id_event") },
            inverseJoinColumns = { @JoinColumn(name = "id_player") })
    private Set<Player> players = new HashSet<Player>();

    public long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getContent(){return content;}

    public void setContent(String content){this.content = content;}

    public Set<Player> getPlayers(){return players;}

    public void setPlayers(Set<Player> players) {this.players = players;}
}
