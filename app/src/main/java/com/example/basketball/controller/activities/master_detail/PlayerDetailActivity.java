package com.example.basketball.controller.activities.master_detail;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavouritePlayerManager.getInstance(getApplicationContext()).
                        createFavouritePlayer(PlayerDetailActivity.this, new FavouritePlayer(player));
            }
        });

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
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

        FavouritePlayerManager.getInstance(getApplicationContext()).
                getFavPlayerExists(PlayerDetailActivity.this, player.getId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, PlayerListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSuccess(List<FavouritePlayer> playerList) {}

    @Override
    public void onFailure(Throwable t) {
        System.out.println("!!!!!!!!! falla" +t);
    }

    @Override
    public void onSuccess(FavouritePlayer favouritePlayer) {
        // SI ESTÁ VOTADO
        System.out.println(favouritePlayer);
        if(favouritePlayer != null){
            Drawable d = getResources().getDrawable(android.R.drawable.star_big_on);
            fab.setImageDrawable(d);
        }
    }
    @Override
    public void onSuccess() {
        System.out.println("HA FUNCIONADO EL CREATE FAVOURITE");
    }
}
