package com.example.basketball.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.basketball.R;
import com.example.basketball.controller.activities.main.MainActivity;
import com.example.basketball.controller.managers.TeamCallback;
import com.example.basketball.controller.managers.TeamManager;
import com.example.basketball.model.Team;

import java.util.List;

public class CreateTeam extends AppCompatActivity implements TeamCallback{

    public Button crear;
    public AutoCompleteTextView nombre, ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_team);

        crear = (Button) findViewById(R.id.crear_equipo);
        nombre = (AutoCompleteTextView) findViewById(R.id.nombre);
        ciudad = (AutoCompleteTextView) findViewById(R.id.ciudad);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreEq = nombre.getText().toString();
                String ciudadEq = ciudad.getText().toString();
                Team team = new Team(nombreEq, ciudadEq);
                TeamManager.getInstance(getApplicationContext()).createTeam(CreateTeam.this, team);
            }
        });
    }

    @Override
    public void onSuccess(List<Team> teamList) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onSuccess(Team team) {
        /*Intent go = new Intent(this, MainActivity.class);
        Bundle b = new Bundle();
        b.putString();
        b.putString("condition","tab2");
        go.putExtras(b);
        startActivity(go);*/
        Intent main = new Intent(CreateTeam.this, MainActivity.class);
        main.putExtra("tab", "teams");
        startActivity(main);
       // startActivity(new Intent(CreateTeam.this, MainActivity.class));
        // redirigir a tab2

    }
}