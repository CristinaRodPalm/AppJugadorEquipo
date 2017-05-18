package com.example.basketball.controller.activities.master_detail;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.basketball.R;
import com.example.basketball.controller.managers.FavouritePlayerCallback;
import com.example.basketball.controller.managers.FavouritePlayerManager;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.FavouritePlayer;
import com.example.basketball.model.Player;

import java.util.List;

public class PlayerDetailActivity extends AppCompatActivity implements FavouritePlayerCallback {

    public static String ARG_ITEM_ID = "item_id";
    public Player player;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // floating button para votar al player
        fab = (FloatingActionButton) findViewById(R.id.fab);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(PlayerDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PlayerDetailFragment.ARG_ITEM_ID));
            player = PlayerManager.getInstance(getApplicationContext()).getPlayer(arguments.getString(ARG_ITEM_ID));
            PlayerDetailFragment fragment = new PlayerDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.player_detail_container, fragment)
                    .commit();
        }else{
            player = new Player();
        }

        FavouritePlayerManager.getInstance(getApplicationContext()).getAllFavouritePlayer(PlayerDetailActivity.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, PlayerListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(List<FavouritePlayer> playerList) {
        System.out.println("plist: "+playerList);
        boolean existe = false;
        for(FavouritePlayer fav: playerList){
            if(fav.getPlayer().getId() == player.getId()){
                existe = true;
                break;
            }
        }
        if(existe){
            Drawable d = getResources().getDrawable(android.R.drawable.star_big_on);
            fab.setImageDrawable(d);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ya está votado -> quitar el voto????
                     Snackbar.make(view, "Ya tienes a éste jugador en favoritos", Snackbar.LENGTH_LONG)
                        .setAction("", null).show();
                }
            });

        }else{
            Drawable d = getResources().getDrawable(android.R.drawable.star_big_off);
            fab.setImageDrawable(d);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Se vota!!
                    FavouritePlayerManager.getInstance(getApplicationContext()).
                            createFavouritePlayer(PlayerDetailActivity.this, new FavouritePlayer(player));

                }
            });
        }
    }

    @Override
    public void onFailure(Throwable t) {}

    @Override
    public void onSuccess(FavouritePlayer favouritePlayer) {}
    @Override
    public void onSuccess() {
        Drawable d = getResources().getDrawable(android.R.drawable.star_big_on);
        fab.setImageDrawable(d);
        Snackbar.make(fab, "Añadido a favoritos", Snackbar.LENGTH_LONG)
                .setAction("", null).show();
        FavouritePlayerManager.getInstance(getApplicationContext()).getAllFavouritePlayer(PlayerDetailActivity.this);
    }
}
