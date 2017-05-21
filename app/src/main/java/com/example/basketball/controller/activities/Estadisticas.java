package com.example.basketball.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.basketball.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

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

        // GRÁFICO
        //https://github.com/PhilJay/MPAndroidChart/wiki/Getting-Started
        LineChart chart = (LineChart) findViewById(R.id.chart);

        int[] data = {1, 4, 2, 5, 9};
        List<Entry> entries = new ArrayList<Entry>();

        for(int d: data){
            entries.add(new Entry(d, d));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "label");
        lineDataSet.setColor(336);
        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);
        chart.invalidate();


    }
}
