package com.example.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.example.basketball.controller.services.FavouritePlayerService;
import com.example.basketball.model.FavouritePlayer;
import com.example.basketball.util.CustomProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavouritePlayerManager {
    private static FavouritePlayerManager ourInstance;
    private Retrofit retrofit;
    private Context context;
    private FavouritePlayerService favouritePlayerService;
    List<FavouritePlayer> favPlayers;
    FavouritePlayer favPlayer;

    private FavouritePlayerManager(Context cntxt) {
        context = cntxt;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        favouritePlayerService = retrofit.create(FavouritePlayerService.class);
    }

    public static FavouritePlayerManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new FavouritePlayerManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    public synchronized void createFavouritePlayer(final FavouritePlayerCallback favouritePlayerCallback, final FavouritePlayer favouritePlayer) {
        Call<FavouritePlayer> callCreate = favouritePlayerService.createFavouritePlayer(UserLoginManager.getInstance(context).getBearerToken(), favouritePlayer);
        callCreate.enqueue(new Callback<FavouritePlayer>() {
            @Override
            public void onResponse(Call<FavouritePlayer> call, Response<FavouritePlayer> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("FavouritePlayer->", "createFavouritePlayer: OK" + 100);
                    favouritePlayerCallback.onSuccess();
                } else {
                    favouritePlayerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<FavouritePlayer> call, Throwable t) {
                Log.e("FavPlayerManager->", "createFavouritePlayer: " + t);

                favouritePlayerCallback.onFailure(t);
            }
        });
    }

    public FavouritePlayer getFavouritePlayer(String id) {
        for (FavouritePlayer favouritePlayer : favPlayers) {
            if (favouritePlayer.getId().toString().equals(id)) {
                return favouritePlayer;
            }
        }
        return null;
    }

    public synchronized void getFavPlayerExists(final FavouritePlayerCallback favouritePlayerCallback, Long id){
        Call<FavouritePlayer> callFavPlayerExists =
                favouritePlayerService.getFavouritePlayerExists(id, UserLoginManager.getInstance(context).getBearerToken());
        callFavPlayerExists.enqueue(new Callback<FavouritePlayer>() {
            @Override
            public void onResponse(Call<FavouritePlayer> call, Response<FavouritePlayer> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    System.out.println("!!!!!!!!!!MANAGER!!!!!!!!!!!!! ->  " + favPlayer);
                    favouritePlayerCallback.onSuccess(favPlayer);
                } else {
                    favouritePlayerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<FavouritePlayer> call, Throwable t) {
                Log.e("PlayerManager->", "getFavPlayerExists()->ERROR: " + t);

                favouritePlayerCallback.onFailure(t);
            }
        });
    }


    public synchronized void getAllFavouritePlayer(final FavouritePlayerCallback favouritePlayerCallback) {
        Call<List<FavouritePlayer>> callGetAll = favouritePlayerService.getAllFavouritePlayer(UserLoginManager.getInstance(context).getBearerToken());

        callGetAll.enqueue(new Callback<List<FavouritePlayer>>() {
            @Override
            public void onResponse(Call<List<FavouritePlayer>> call, Response<List<FavouritePlayer>> response) {
                System.out.println("GET ALL FAV PLAYERS \n Code: " + response.code() + "\n"
                        + response.body());
                int code = response.code();

                if (code == 200 || code == 201) {
                    favouritePlayerCallback.onSuccess(response.body());
                } else {
                    favouritePlayerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<FavouritePlayer>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                favouritePlayerCallback.onFailure(t);
            }
        });
    }

}
