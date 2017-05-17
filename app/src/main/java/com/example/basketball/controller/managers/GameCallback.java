package com.example.basketball.controller.managers;

import com.example.basketball.model.Game;

import java.util.List;

/**
 * Created by User on 17/05/2017.
 */
public interface GameCallback {
    void onSuccessList(List<Game> playerList);

    void onFailure(Throwable t);

    void onSuccess(Game player);
}
