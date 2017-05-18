package com.example.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.example.basketball.controller.services.GameService;
import com.example.basketball.model.Game;
import com.example.basketball.util.CustomProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 17/05/2017.
 */
public class GameManager {
    private static GameManager ourInstance;
    private Retrofit retrofit;
    private Context context;
    private GameService gameService;
    List<Game> games;

    private GameManager(Context cntxt) {
        context = cntxt;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gameService = retrofit.create(GameService.class);
    }

    public static GameManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new GameManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    public Game getGame(String id){
        for(Game game: games){
            if(game.getId().toString().equals(id)){
                return game;
            }
        }
        return null;
    }

    public synchronized void getAllGames(final GameCallback gameCallback) {
        Call<List<Game>> callGetAll = gameService.getAllGames(UserLoginManager.getInstance(context).getBearerToken());

        callGetAll.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                System.out.println("GET ALL PLAYERS \n Code: " + response.code() + "\n"
                        + response.body());

                games = response.body();
                int code = response.code();

                if (code == 200 || code == 201) {
                    gameCallback.onSuccessList(games);
                } else {
                    gameCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Log.e("PlayerManager->", "getAllPlayers()->ERROR: " + t);

                gameCallback.onFailure(t);
            }
        });
    }

    public synchronized void createGame(final GameCallback gameCallback, Game game){
        Call<Game> callCreateGame = gameService.createGame(game, UserLoginManager.getInstance(context).getBearerToken());

        callCreateGame.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                System.out.println("CREATE GAME \n" +
                        "Code: "+response.code()+"\n"
                        +response.body());
                System.out.println(response.raw().message());
                gameCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                System.out.println("CREATE GAME --> ERROR \n" + t.getMessage());
            }
        });
    }
}
