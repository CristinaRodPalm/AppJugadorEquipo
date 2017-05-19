package com.example.basketball.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.basketball.R;

public class Estadisticas extends AppCompatActivity {

    public Button top5masvotados, jugByPosicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);

        top5masvotados = (Button) findViewById(R.id.top5masvotados);
        top5masvotados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Estadisticas.this, Top5Votados.class));
            }
        });
        jugByPosicion = (Button) findViewById(R.id.jugByPosicion);
        jugByPosicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Estadisticas.this, PlayersByPosicion.class));
            }
        });

    }
}
