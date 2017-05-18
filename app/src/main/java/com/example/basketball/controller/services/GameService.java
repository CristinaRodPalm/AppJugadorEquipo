package com.example.basketball.controller.services;

import com.example.basketball.model.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by User on 17/05/2017.
 */
public interface GameService {
    @GET("/api/games")
    Call<List<Game>> getAllGames(
            @Header("Authorization") String Authorization
    );

    @POST("/api/games")
    Call<Game> createGame(
            @Body Game game,
            @Header("Authorization") String Authorization);

}
