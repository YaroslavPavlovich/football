package com.example.football.core.team;

import com.example.football.core.tournament.Tournament;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "fc_team")
public class Team {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "command_name")
        private String name;

        @Column(name = "players")
        private int numPlayers;

        @OneToOne(mappedBy = "passport")

        public long getId(){return id;}

        public void setId(Long id){this.id = id;}

        public String getName(){return name;}

        public void setName(String name){this.name = name;}

        public int getNumPlayers(){return numPlayers;}

        public void setNumPlayers(int numPlayers){this.numPlayers = numPlayers;}

        /*public Set<Tournament> getTournaments() {
                return tournaments;
        }

        public void setSetTournaments(Set<Tournament> tournaments) {
                this.tournaments = tournaments;
        }*/
    }
