package com.example.basketball.controller.services;

import com.example.basketball.model.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlayerService {
    @GET("/api/players")
    Call<List<Player>> getAllPlayer(
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization
    );

    @GET("/api/players")
    Call<Player> getPlayer(
            @Path("id") Long id,
            @Header("Authorization") String Authorization
    );

    @POST("/api/players")
    Call<Player> createPlayer(
            @Body Player player,
            @Header("Authorization") String Authorization
    );
}
