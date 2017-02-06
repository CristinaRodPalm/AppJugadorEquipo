package com.example.basketball.controller.activities.master_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.activities.login.LoginActivity;
import com.example.basketball.controller.managers.PlayerCallback;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.Player;

import java.util.List;

public class PlayerListActivity extends AppCompatActivity implements PlayerCallback {

    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // La acción del botón flotante -> add player al equipo
        FloatingActionButton addPlayer = (FloatingActionButton) findViewById(R.id.addPlayer);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding a new Player", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.playersList);
        assert recyclerView != null;

        if (findViewById(R.id.player_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        PlayerManager.getInstance(this.getApplicationContext()).getAllPlayers(PlayerListActivity.this);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.i("setupRecyclerView", "\n" + players);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(players));
    }

    // Funciona el get
    @Override
    public void onSuccess(List<Player> playerList) {
        players = playerList;
        setupRecyclerView(recyclerView);
    }

    // No funciona el get
    @Override
    public void onFailure(Throwable t) {
        Intent i = new Intent(PlayerListActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    // Get de un sólo jugador, no sirve
    @Override
    public void onSuccess(Player player) {}

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Player> players;

        // Para saber cuantas posiciones debe tener el recycler
        public SimpleItemRecyclerViewAdapter(List<Player> items) {
            players = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.player_list_content, parent, false);
            return new ViewHolder(view);
        }

        // Para saber cuántos players recibimos
        @Override
        public int getItemCount() {
            return players.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View vista;
            public final TextView idPlayer;
            public final TextView nombrePlayer;
            public Player playerSeleccionado;

            public ViewHolder(View view) {
                super(view);
                vista = view;
                idPlayer = (TextView) view.findViewById(R.id.id);
                nombrePlayer = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + nombrePlayer.getText() + "'";
            }
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.playerSeleccionado = players.get(position);
            holder.idPlayer.setText(players.get(position).getId().toString());
            holder.nombrePlayer.setText(players.get(position).getName()+" "+players.get(position).getSurname());

            holder.vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(PlayerDetailFragment.ARG_ITEM_ID, holder.playerSeleccionado.getId().toString());
                        PlayerDetailFragment fragment = new PlayerDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.player_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, PlayerDetailActivity.class);
                        intent.putExtra(PlayerDetailFragment.ARG_ITEM_ID, holder.playerSeleccionado.getId().toString());

                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
