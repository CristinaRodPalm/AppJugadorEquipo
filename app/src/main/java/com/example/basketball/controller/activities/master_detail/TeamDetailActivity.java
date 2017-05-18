package com.example.basketball.controller.activities.master_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.basketball.R;
import com.example.basketball.controller.managers.TeamCallback;
import com.example.basketball.controller.managers.TeamManager;
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

        if (savedInstanceState == null) {
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
