package com.example.basketball.controller.services;

import com.example.basketball.model.FavouritePlayer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
            /**
             * "Bearer [space ]token"
             */
            @Header("Authorization") String Authorization
    );

    @GET("/api/favourite-players/favExist/{id}")
    Call<FavouritePlayer> getFavouritePlayerExists(
            @Path("id") Long id,
            @Header("Authorization") String Authorization
    );
}
