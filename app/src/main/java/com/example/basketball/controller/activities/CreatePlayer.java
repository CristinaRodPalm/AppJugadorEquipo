package com.example.basketball.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.basketball.R;
import com.example.basketball.controller.activities.main.MainActivity;
import com.example.basketball.controller.managers.PlayerCallback;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.controller.managers.TeamCallback;
import com.example.basketball.controller.managers.TeamManager;
import com.example.basketball.model.Player;
import com.example.basketball.model.Position;
import com.example.basketball.model.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreatePlayer extends AppCompatActivity implements PlayerCallback, TeamCallback {

    public Button crear;
    public AutoCompleteTextView nombre, apellido;
    //public DatePicker nacimiento;
    public EditText canastas, asistencias, rebotes;
    public Spinner equipos, posiciones;
    String[] positions;
    Position posElegida;
    Team teamElegido;
    List<String> teams = new ArrayList<>();
    List<Team> teamsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);

        crear = (Button) findViewById(R.id.crear_jugador);
        equipos = (Spinner) findViewById(R.id.equipo);
        posiciones = (Spinner) findViewById(R.id.posicion);
        nombre = (AutoCompleteTextView) findViewById(R.id.nombre);
        apellido = (AutoCompleteTextView) findViewById(R.id.apellido);
        canastas = (EditText) findViewById(R.id.canastas);
        asistencias = (EditText) findViewById(R.id.asistencias);
        rebotes = (EditText) findViewById(R.id.rebotes);

        positions = getNames(Position.class);

        ArrayAdapter adapterPosiciones = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, positions);
        posiciones.setAdapter(adapterPosiciones);
        posiciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(positions[position].equals("ALERO")) posElegida = Position.ALERO;
                else if(positions[position].equals("ALA")) posElegida = Position.ALA;
                else if(positions[position].equals("BASE")) posElegida = Position.BASE;
                else if(positions[position].equals("PIVOT")) posElegida = Position.PIVOT;
                System.out.println(posElegida);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { } //NADA
        });

        TeamManager.getInstance(this.getApplicationContext()).getAllTeams(CreatePlayer.this);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nombre.getText().toString();
                String surname = apellido.getText().toString();
                int baskets = Integer.parseInt(canastas.getText().toString());
                int assists = Integer.parseInt(asistencias.getText().toString());
                int rebounds = Integer.parseInt(rebotes.getText().toString());
                Player p = new Player(name, surname, baskets, assists, rebounds, posElegida, teamElegido);
                PlayerManager.getInstance(getApplicationContext()).createPlayer(CreatePlayer.this, p);
            }
        });
    }

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }

    // este no debería ir
    @Override
    public void onSuccessList(List<Player> playerList) { }

    @Override
    public void onSuccess(List<Team> teamList) {
        teamsArray = teamList;
        for (Team team : teamList) {
            teams.add(team.getName());
        }
        ArrayAdapter adapterEquipos = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, teams);
        equipos.setAdapter(adapterEquipos);
        equipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(Team team: teamsArray){
                    if(teams.get(position) == team.getName()) teamElegido = team;
                }
                System.out.println(teamElegido);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { } //NADA
        });

    }

    // no funciona el crear
    @Override
    public void onFailure(Throwable t) { }

    @Override
    public void onSuccess(Team team) { }

    // ESTE SÍ -> INTENT PARA VOLVER A LA LISTA!!
    @Override
    public void onSuccess(Player player) {
        startActivity(new Intent(CreatePlayer.this, MainActivity    .class));
    }
}
