package com.example.basketball.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.basketball.R;
import com.example.basketball.controller.activities.login.LoginActivity;
import com.example.basketball.controller.activities.master_detail.PlayerListActivity;
import com.example.basketball.controller.managers.PlayerCallback;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.Player;
import com.example.basketball.model.Position;
import com.example.basketball.model.Team;

import java.util.Date;
import java.util.List;

public class CreatePlayer extends AppCompatActivity implements PlayerCallback {

    public Button crear;
    Player p;
    public AutoCompleteTextView nombre, apellido, posicion, equipo;
    public DatePicker nacimiento;
    public EditText canastas, asistencias, rebotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);

        crear = (Button) findViewById(R.id.crear_jugador);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = new Player("Grace", "Villareal", 66, 28, 84, Position.BASE);
                crearJugador(p);
            }
        });
    }

    public void crearJugador(Player p) {
        PlayerManager.getInstance(this.getApplicationContext()).createPlayer(CreatePlayer.this, p);
    }

    // este no debería ir
    @Override
    public void onSuccess(List<Player> playerList) {

    }

    // no funciona el crear
    @Override
    public void onFailure(Throwable t) {

    }

    // ESTE SÍ -> INTENT PARA VOLVER A LA LISTA!!
    @Override
    public void onSuccess(Player player) {
        startActivity(new Intent(CreatePlayer.this, PlayerListActivity.class));
    }
}
