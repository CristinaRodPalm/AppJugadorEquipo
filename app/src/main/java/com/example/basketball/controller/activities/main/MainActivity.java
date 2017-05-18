package com.example.basketball.controller.activities.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TabHost;

import com.example.basketball.R;
import com.example.basketball.controller.activities.Estadisticas;
import com.example.basketball.controller.activities.master_detail.FavPlayerListActivity;
import com.example.basketball.controller.activities.master_detail.GameListActivity;
import com.example.basketball.controller.activities.master_detail.PlayerListActivity;
import com.example.basketball.controller.activities.master_detail.TeamListActivity;
import com.example.basketball.controller.managers.UserLoginManager;
import com.example.basketball.model.UserToken;

public class MainActivity extends TabActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, PlayerListActivity.class);
        spec = tabHost.newTabSpec("Jugadores").setIndicator("Jugadores")
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, TeamListActivity.class);
        spec = tabHost.newTabSpec("Equipos").setIndicator("Equipos")
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, GameListActivity.class);
        spec = tabHost.newTabSpec("Partidos").setIndicator("Partidos")
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, FavPlayerListActivity.class);
        spec = tabHost.newTabSpec("Favoritos").setIndicator("Favoritos")
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Estadisticas.class);
        spec = tabHost.newTabSpec("Estadísticas").setIndicator("Estadísticas")
                .setContent(intent);
        tabHost.addTab(spec);

    }

    @Override
    protected void onResume() {
        super.onResume();

        UserToken userToken = UserLoginManager.getInstance(this.getApplicationContext()).getUserToken();

        if (userToken == null) {
            Log.e("MainActivity->", "onResume ERROR: userToken is NULL");
        }
    }

}
