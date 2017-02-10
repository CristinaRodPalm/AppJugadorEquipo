package com.example.basketball.controller.activities.master_detail;

import android.content.Intent;
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
import com.example.basketball.controller.managers.TeamCallback;
import com.example.basketball.controller.managers.TeamManager;
import com.example.basketball.model.FavouritePlayer;
import com.example.basketball.model.Player;
import com.example.basketball.model.Team;

import java.util.List;

public class TeamDetailActivity extends AppCompatActivity implements TeamCallback {

    public static String ARG_ITEM_ID = "item_id";
    public Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // floating button para votar al player
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Team votado");
                /*FavouritePlayerManager.getInstance(getApplicationContext()).
                        createFavouritePlayer(TeamDetailActivity.this, new FavouritePlayer(player));*/
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
            arguments.putString(TeamDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(TeamDetailFragment.ARG_ITEM_ID));
            team = TeamManager.getInstance(getApplicationContext()).getTeam(arguments.getString(ARG_ITEM_ID));
            TeamDetailFragment fragment = new TeamDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.team_detail_container, fragment)
                    .commit();
        }else{
            team = new Team();
        }
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
            navigateUpTo(new Intent(this, TeamListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSuccess(List<Team> teamList) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onSuccess(Team team) {

    }

}
