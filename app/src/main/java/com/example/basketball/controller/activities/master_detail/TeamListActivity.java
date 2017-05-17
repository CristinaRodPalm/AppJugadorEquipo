package com.example.basketball.controller.activities.master_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.activities.CreateTeam;
import com.example.basketball.controller.activities.login.LoginActivity;
import com.example.basketball.controller.managers.TeamCallback;
import com.example.basketball.controller.managers.TeamManager;
import com.example.basketball.model.Team;

import java.util.List;

public class TeamListActivity extends AppCompatActivity implements TeamCallback {

    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private List<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        // La acción del botón flotante -> add equipo
        FloatingActionButton addPlayer = (FloatingActionButton) findViewById(R.id.addTeam);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TeamListActivity.this, CreateTeam.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.teamsList);
        assert recyclerView != null;

        if (findViewById(R.id.player_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        TeamManager.getInstance(this.getApplicationContext()).getAllTeams(TeamListActivity.this);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.i("setupRecyclerView", "\n" + teams);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(teams));
    }

    // Funciona el get
    @Override
    public void onSuccess(List<Team> teamsList) {
        teams = teamsList;
        setupRecyclerView(recyclerView);
    }

    // No funciona el get
    @Override
    public void onFailure(Throwable t) {
        Intent getLogin = new Intent(TeamListActivity.this, LoginActivity.class);
        startActivity(getLogin);
        finish();
    }

    @Override
    public void onSuccess(Team team) {}

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Team> teams;

        // Para saber cuantas posiciones debe tener el recycler
        public SimpleItemRecyclerViewAdapter(List<Team> items) {
            teams = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.teams_list_content, parent, false);
            return new ViewHolder(view);
        }

        // Para saber cuántos teams recibimos
        @Override
        public int getItemCount() {
            return teams.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View vista;
            public final TextView idTeam;
            public final TextView nombreTeam;
            public Team teamSeleccionado;

            public ViewHolder(View view) {
                super(view);
                vista = view;
                idTeam = (TextView) view.findViewById(R.id.id);
                nombreTeam = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + nombreTeam.getText() + "'";
            }
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.teamSeleccionado = teams.get(position);
            final String teamID = holder.teamSeleccionado.getId().toString();
            holder.idTeam.setText(teams.get(position).getId().toString());
            holder.nombreTeam.setText(teams.get(position).getName());

            holder.vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(TeamDetailFragment.ARG_ITEM_ID, teamID);
                        TeamDetailFragment fragment = new TeamDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.player_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, TeamDetailActivity.class);
                        intent.putExtra(TeamDetailFragment.ARG_ITEM_ID, teamID);

                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
