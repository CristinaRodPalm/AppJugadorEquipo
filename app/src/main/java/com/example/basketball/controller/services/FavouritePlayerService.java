package com.example.basketball.controller.services;

import com.example.basketball.model.FavouritePlayer;
import com.example.basketball.model.PlayerDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Alfredo on 28/02/2016.
 */
public interface FavouritePlayerService {
    @POST("/api/favourite-players")
    Call<FavouritePlayer> createFavouritePlayer(
            @Header("Authorization") String Authorization,
            @Body FavouritePlayer favouritePlayer
    );
    @GET("/api/favourite-players")
    Call<List<FavouritePlayer>> getAllFavouritePlayer(
            @Header("Authorization") String Authorization
    );

    @GET("/api/top-players")
    Call<List<PlayerDTO>> getTop5Player(
            @Header("Authorization") String Authorization
    );

}
