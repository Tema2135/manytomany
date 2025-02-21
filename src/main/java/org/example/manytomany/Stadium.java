package org.example.manytomany;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Stadium")
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stadiumId;

    private String name;

    @ManyToMany(mappedBy = "stadiums")
    private List<Team> teams= new ArrayList<>();

    public Stadium() {}

    public Stadium(String name, Long stadiumId, List<Team> teams) {
        this.name = name;
        this.stadiumId = stadiumId;
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
