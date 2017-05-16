package com.example.basketball.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Team.
 */

public class Team implements Serializable {
    private Long id;
    private String name;
    private String city;
    private Set<Game> gameLocalTeams = new HashSet<>();
    private Set<Game> gameVisitorTeams = new HashSet<>();
    private Set<Player> players = new HashSet<>();

    public Team() {
    }

    public Team(Long id, String name, String city, Set<Game> gameLocalTeams, Set<Game> gameVisitorTeams, Set<Player> players) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.gameLocalTeams = gameLocalTeams;
        this.gameVisitorTeams = gameVisitorTeams;
        this.players = players;
    }
    public Team(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Game> getGameLocalTeams() {
        return gameLocalTeams;
    }

    public void setGameLocalTeams(Set<Game> gameLocalTeams) {
        this.gameLocalTeams = gameLocalTeams;
    }

    public Set<Game> getGameVisitorTeams() {
        return gameVisitorTeams;
    }

    public void setGameVisitorTeams(Set<Game> gameVisitorTeams) {
        this.gameVisitorTeams = gameVisitorTeams;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
