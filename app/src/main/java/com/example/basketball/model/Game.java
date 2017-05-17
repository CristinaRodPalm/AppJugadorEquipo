package com.example.basketball.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A Game.
 */
public class Game implements Serializable {
    private Long id;
    private String name;
    private Integer localScore;
    private Integer visitorScore;
    private Date startTime;
    private Date finishTime;
    private Team gameLocalTeam;
    private Team gameVisitorTeam;
    private Set<GameRating> gameRatings = new HashSet<>();

    public Game() {
    }

    public Game(Long id, String name, Integer localScore, Integer visitorScore, Date startTime, Date finishTime, Team gameLocalTeam, Team gameVisitorTeam, Set<GameRating> gameRatings) {
        this.id = id;
        this.name = name;
        this.localScore = localScore;
        this.visitorScore = visitorScore;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.gameLocalTeam = gameLocalTeam;
        this.gameVisitorTeam = gameVisitorTeam;
        this.gameRatings = gameRatings;
    }

    public Game(Long id, String name, Integer localScore, Integer visitorScore, Date startTime, Date finishTime) {
        this.id = id;
        this.name = name;
        this.localScore = localScore;
        this.visitorScore = visitorScore;
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

    public Integer getLocalScore() {
        return localScore;
    }

    public void setLocalScore(Integer localScore) {
        this.localScore = localScore;
    }

    public Integer getVisitorScore() {
        return visitorScore;
    }

    public void setVisitorScore(Integer visitorScore) {
        this.visitorScore = visitorScore;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Team getGameLocalTeam() {
        return gameLocalTeam;
    }

    public void setGameLocalTeam(Team gameLocalTeam) {
        this.gameLocalTeam = gameLocalTeam;
    }

    public Team getGameVisitorTeam() {
        return gameVisitorTeam;
    }

    public void setGameVisitorTeam(Team gameVisitorTeam) {
        this.gameVisitorTeam = gameVisitorTeam;
    }

    public Set<GameRating> getGameRatings() {
        return gameRatings;
    }

    public void setGameRatings(Set<GameRating> gameRatings) {
        this.gameRatings = gameRatings;
    }
}
