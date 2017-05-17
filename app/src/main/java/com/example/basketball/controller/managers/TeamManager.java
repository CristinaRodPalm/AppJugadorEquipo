package com.example.basketball.controller.managers;

import android.content.Context;
import android.util.Log;

import com.example.basketball.controller.services.TeamService;
import com.example.basketball.model.Team;
import com.example.basketball.util.CustomProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamManager {
    private static TeamManager ourInstance;
    private Retrofit retrofit;
    private Context context;
    private TeamService teamService;
    List<Team> teams;


    private TeamManager(Context cntxt) {
        context = cntxt;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.getInstance(context).get("app.baseUrl"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        teamService = retrofit.create(TeamService.class);
    }

    public static TeamManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new TeamManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    public synchronized void getAllTeams(final TeamCallback teamCallback) {
        Call<List<Team>> callGetAll = teamService.getAllTeams(UserLoginManager.getInstance(context).getBearerToken());

        callGetAll.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                System.out.println("GET ALL TEAMS \n Code: " + response.code() + "\n"
                        + response.body());

                 teams = response.body();
                int code = response.code();

                if (code == 200 || code == 201) {
                    teamCallback.onSuccess(teams);
                } else {
                    teamCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.e("TeamManager->", "getAllTeams()->ERROR: " + t);
                teamCallback.onFailure(t);
            }
        });
    }

    public Team getTeam(String id) {
        for (Team team: teams){
            if(team.getId().toString().equals(id)){
                return team;
            }
        }
        return null;
    }

    public synchronized void createTeam(final TeamCallback teamCallback,Team team) {
        Call<Team> call = teamService.createTeam(UserLoginManager.getInstance(context).getBearerToken(), team);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Team->","createTeam: " + Integer.toString(code));
                    teamCallback.onSuccess(response.body());
                } else {
                    teamCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.e("TeamManager->",  t.toString());

                teamCallback.onFailure(t);
            }
        });
    }

}
