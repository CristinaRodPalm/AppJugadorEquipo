package com.example.basketball.controller.managers;

import com.example.basketball.model.Player;

import java.util.List;

public interface PlayerCallback {
    void onSuccessList(List<Player> playerList);

    void onFailure(Throwable t);

    void onSuccess(Player player);
}
