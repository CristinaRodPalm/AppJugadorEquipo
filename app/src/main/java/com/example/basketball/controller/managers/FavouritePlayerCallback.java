package com.example.basketball.controller.managers;

import com.example.basketball.model.FavouritePlayer;

import java.util.List;

public interface FavouritePlayerCallback {
    void onSuccess(List<FavouritePlayer> playerList);

    void onFailure(Throwable t);

    void onSuccess(FavouritePlayer favouritePlayer);

    void onSuccess();
}
