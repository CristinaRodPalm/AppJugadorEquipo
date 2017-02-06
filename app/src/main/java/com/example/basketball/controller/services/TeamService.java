package com.example.basketball.controller.services;

import com.example.basketball.model.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Alfredo on 28/02/2016.
 */
public interface TeamService {
    @GET("/api/teams")
    Call<List<Team>> getAllTeams(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization
    );

    @GET("/api/teams/{id}")
    Call<Team> getTeam(
            @Path("id") Long id,
            @Header("Authorization") String Authorization
    );
}
