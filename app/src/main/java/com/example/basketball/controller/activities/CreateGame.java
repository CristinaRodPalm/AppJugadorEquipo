package com.example.basketball.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.basketball.R;
import com.example.basketball.controller.activities.main.MainActivity;
import com.example.basketball.controller.managers.GameCallback;
import com.example.basketball.controller.managers.GameManager;
import com.example.basketball.controller.managers.TeamCallback;
import com.example.basketball.controller.managers.TeamManager;
import com.example.basketball.model.Game;
import com.example.basketball.model.Team;

import java.util.ArrayList;
import java.util.List;

public class CreateGame extends AppCompatActivity implements GameCallback, TeamCallback {

    public Spinner localTeam, visitorTeam;
    public EditText localScore, visitorScore;
    public Button crear;
    List<Team> teamArray = new ArrayList<>();
    List<String> teams = new ArrayList<>();
    Team localSelect, visitorSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_game);

        localTeam = (Spinner) findViewById(R.id.localTeam);
        visitorTeam = (Spinner) findViewById(R.id.visitorTeam);
        localScore = (EditText) findViewById(R.id.localScore);
        visitorScore = (EditText) findViewById(R.id.visitorScore);

        // si funciona en el onSuccess crearemos los Spinner local y visitor team
        TeamManager.getInstance(this.getApplicationContext()).getAllTeams(CreateGame.this);

        crear = (Button) findViewById(R.id.crear_partido);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = localSelect.getName().toString()+" - "+visitorSelect.getName().toString();
                int local = Integer.parseInt(localScore.getText().toString());
                int visitor = Integer.parseInt(visitorScore.getText().toString());
                Game partido = new Game(nombre, local, visitor, localSelect, visitorSelect);
                GameManager.getInstance(getApplicationContext()).createGame(CreateGame.this, partido);
            }
        });
    }


    @Override
    public void onSuccessList(List<Game> gamesList) { }

    // FUNCIONARÁ ESTE
    @Override
    public void onSuccess(List<Team> teamList) {
        teamArray = teamList;
        for (Team team : teamList) {
            teams.add(team.getName());
        }
        ArrayAdapter localAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, teams);
        ArrayAdapter visitorAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, teams);
        localTeam.setAdapter(localAdapter);visitorTeam.setAdapter(visitorAdapter);
        localTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (Team team : teamArray) {
                    if (teams.get(position) == team.getName()) localSelect = team;
                }
                System.out.println(localSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        visitorTeam.setAdapter(visitorAdapter);
        visitorTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (Team team : teamArray) {
                    if (teams.get(position) == team.getName()) visitorSelect = team;
                }
                System.out.println(visitorSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onFailure(Throwable t) { }

    @Override
    public void onSuccess(Team team) { }

    // Y ESTE SI SE CONSIGUE CREAR EL GAME
    @Override
    public void onSuccess(Game game) {
        startActivity(new Intent(CreateGame.this, MainActivity.class));
        // redirigir a tab3
    }
}