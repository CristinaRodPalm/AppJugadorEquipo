package com.example.basketball.model;

    public class PlayerDTO {
        private Player player;
        private Long numFavs;

    public PlayerDTO() {
    }

    public PlayerDTO(Player player, Long numFavs) {
        this.player = player;
        this.numFavs = numFavs;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getNumFavs() {
        return numFavs;
    }

    public void setNumFavs(Long numFavs) {
        this.numFavs = numFavs;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "player=" + player +
                ", numFavs=" + numFavs +
                '}';
    }
}

