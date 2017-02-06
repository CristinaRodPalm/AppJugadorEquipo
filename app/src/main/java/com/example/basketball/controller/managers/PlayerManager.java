package com.example.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.example.basketball.controller.services.PlayerService;
import com.example.basketball.model.Player;
import com.example.basketball.util.CustomProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerManager {
    private static PlayerManager ourInstance;
    private Retrofit retrofit;
    private Context context;
    private PlayerService playerService;
    List<Player> players;

    private PlayerManager(Context cntxt) {
        context = cntxt;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        playerService = retrofit.create(PlayerService.class);
    }

    public static PlayerManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new PlayerManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    public synchronized void getAllPlayers(final PlayerCallback playerCallback) {
        Call<List<Player>> callGetAll = playerService.getAllPlayer(UserLoginManager.getInstance(context).getBearerToken());

        callGetAll.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                System.out.println("GET ALL PLAYERS \n Code: " + response.code() + "\n"
                        + response.body());

                players = response.body();
                int code = response.code();

                if (code == 200 || code == 201) {
                    playerCallback.onSuccess(players);
                } else {
                    playerCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                playerCallback.onFailure(t);
            }
        });
    }

    public Player getPlayer(String id) {
        for (Player player : players) {
            if (player.getId().toString().equals(id)) {
                return player;
            }
        }

        return null;
    }
}
