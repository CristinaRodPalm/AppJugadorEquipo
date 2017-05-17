package com.example.basketball.controller.services;

import com.example.basketball.model.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by User on 17/05/2017.
 */
public interface GameService {
    @GET("/api/games")
    Call<List<Game>> getAllGames(
            @Header("Authorization") String Authorization
    );

    @GET("/api/games/{id}")
    Call<Game> getGame(
            @Path("id") Long id,
            @Header("Authorization") String Authorization
    );
}
