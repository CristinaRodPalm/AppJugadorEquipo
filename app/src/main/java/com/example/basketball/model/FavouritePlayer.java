package com.example.basketball.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A FavouritePlayer.
 */
public class FavouritePlayer implements Serializable {

    private Long id;
    private Date favouriteDateTime;
    private User user;
    private Player player;

    public FavouritePlayer(Player player) {
        this.player = player;
    }

    public FavouritePlayer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFavouriteDateTime() {
        return favouriteDateTime;
    }

    public FavouritePlayer favouriteDateTime(Date favouriteDateTime) {
        this.favouriteDateTime = favouriteDateTime;
        return this;
    }

    public void setFavouriteDateTime(Date favouriteDateTime) {
        this.favouriteDateTime = favouriteDateTime;
    }

    public User getUser() {
        return user;
    }

    public FavouritePlayer user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Player getPlayer() {
        return player;
    }

    public FavouritePlayer player(Player player) {
        this.player = player;
        return this;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FavouritePlayer favouritePlayer = (FavouritePlayer) o;
        if (favouritePlayer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, favouritePlayer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FavouritePlayer{" +
            "id=" + id +
            ", favouriteDateTime='" + favouriteDateTime + "'" +
            '}';
    }
}
