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
import com.example.basketball.controller.managers.GameManager;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.FavouritePlayer;
import com.example.basketball.model.Game;
import com.example.basketball.model.Player;

import java.util.List;

public class GameDetailActivity extends AppCompatActivity {

    public static String ARG_ITEM_ID = "item_id";
    public Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);

        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(GameDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(GameDetailFragment.ARG_ITEM_ID));
            game = GameManager.getInstance(getApplicationContext()).getGame(arguments.getString(ARG_ITEM_ID));
            GameDetailFragment fragment = new GameDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.game_detail_container, fragment)
                    .commit();
        }else{
            game = new Game();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, GameListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
