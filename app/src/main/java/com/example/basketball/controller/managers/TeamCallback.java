package com.example.basketball.controller.managers;

import com.example.basketball.model.Team;

import java.util.List;

public interface TeamCallback {
    void onSuccess(List<Team> teamList);

    void onFailure(Throwable t);

    void onSuccess(Team team);

}
