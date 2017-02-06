package com.example.basketball.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/**
 * A GameRating.
 */

public class GameRating implements Serializable {

    private Long id;
    private Integer score;
    private Date scoreDateTime;
    private User user;
    private Game game;

    public GameRating() {
    }

    public GameRating(Integer score, Date scoreDateTime, User user, Game game) {
        this.score = score;
        this.scoreDateTime = scoreDateTime;
        this.user = user;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public GameRating score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getScoreDateTime() {
        return scoreDateTime;
    }

    public GameRating scoreDateTime(Date scoreDateTime) {
        this.scoreDateTime = scoreDateTime;
        return this;
    }

    public void setScoreDateTime(Date scoreDateTime) {
        this.scoreDateTime = scoreDateTime;
    }

    public User getUser() {
        return user;
    }

    public GameRating user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public GameRating game(Game game) {
        this.game = game;
        return this;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameRating gameRating = (GameRating) o;
        if (gameRating.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, gameRating.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GameRating{" +
            "id=" + id +
            ", score='" + score + "'" +
            ", scoreDateTime='" + scoreDateTime + "'" +
            '}';
    }
}
