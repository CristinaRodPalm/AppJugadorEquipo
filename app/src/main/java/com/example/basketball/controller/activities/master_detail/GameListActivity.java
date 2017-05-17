package com.example.basketball.controller.activities.master_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.GameCallback;
import com.example.basketball.controller.managers.GameManager;
import com.example.basketball.model.Game;

import java.util.List;

public class GameListActivity extends AppCompatActivity implements GameCallback {

    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private List<Game> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        // La acción del botón flotante -> add player
        FloatingActionButton addPlayer = (FloatingActionButton) findViewById(R.id.addGame);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Snackbar.make(view, "Add game", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
               /* Intent i = new Intent(PlayerListActivity.this, CreatePlayer.class);
                startActivity(i);*/
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.gamesList);
        assert recyclerView != null;

        if (findViewById(R.id.player_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        GameManager.getInstance(this.getApplicationContext()).getAllGames(GameListActivity.this);
    }

    // ESTE DEBERÍA FUNCIONAR
    @Override
    public void onSuccessList(List<Game> gameList) {
        games = gameList;
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(games));
    }

    @Override
    public void onFailure(Throwable t) {}

    @Override
    public void onSuccess(Game player) {}




    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(games));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Game> games;

        // Para saber cuantas posiciones debe tener el recycler
        public SimpleItemRecyclerViewAdapter(List<Game> items) {
            games = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.games_list_content, parent, false);
            return new ViewHolder(view);
        }

        // Para saber cuántos teams recibimos
        @Override
        public int getItemCount() {
            return games.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View vista;
            public final TextView idGame;
            public final TextView nombreGame;
            public Game gameSeleccionado;

            public ViewHolder(View view) {
                super(view);
                vista = view;
                idGame = (TextView) view.findViewById(R.id.id);
                nombreGame = (TextView) view.findViewById(R.id.content);
            }
            @Override
            public String toString() {
                return super.toString() + " '" + nombreGame.getText() + "'";
            }
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.gameSeleccionado = games.get(position);
            final String gameID = holder.gameSeleccionado.getId().toString();
            holder.idGame.setText(games.get(position).getId().toString());
            holder.nombreGame.setText(games.get(position).getName());

            holder.vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(TeamDetailFragment.ARG_ITEM_ID, gameID);
                        GameDetailFragment fragment = new GameDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.game_detail_container, fragment)
                                .commit();

                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, GameDetailActivity.class);
                        intent.putExtra(GameDetailFragment.ARG_ITEM_ID, gameID);

                        context.startActivity(intent);
                    }
                }
            });
        }
    }


}
