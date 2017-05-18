package com.example.basketball.controller.activities.master_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.FavouritePlayerCallback;
import com.example.basketball.controller.managers.FavouritePlayerManager;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.FavouritePlayer;
import com.example.basketball.model.Player;

import java.util.List;

public class FavPlayerListActivity extends AppCompatActivity implements FavouritePlayerCallback {

    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private List<FavouritePlayer> favPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos_activity);

        recyclerView = (RecyclerView) findViewById(R.id.favPlayerList);
        assert recyclerView != null;

        if (findViewById(R.id.player_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        FavouritePlayerManager.getInstance(this.getApplicationContext()).getAllFavouritePlayer(FavPlayerListActivity.this);
    }

    // funcionará este
    @Override
    public void onSuccess(List<FavouritePlayer> playerList) {
        System.out.println(playerList);
        favPlayers = playerList;
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(favPlayers));
    }

    @Override
    public void onFailure(Throwable t) { }

    @Override
    public void onSuccess(FavouritePlayer favouritePlayer) { }

    @Override
    public void onSuccess() { }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(favPlayers));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<FavouritePlayer> favPlayers;

        // Para saber cuantas posiciones debe tener el recycler
        public SimpleItemRecyclerViewAdapter(List<FavouritePlayer> items) {
            favPlayers = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fav_list_content, parent, false);
            return new ViewHolder(view);
        }

        // Para saber cuántos teams recibimos
        @Override
        public int getItemCount() {
            return favPlayers.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View vista;
            public final TextView idPlayer;
            public final TextView nombrePlayer;
            public FavouritePlayer favPlayerSelect;

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
            holder.favPlayerSelect = favPlayers.get(position);
            /*final String playerID = holder.favPlayerSelect.getId().toString();*/
            final String playerID = holder.favPlayerSelect.getPlayer().getId().toString();
            holder.idPlayer.setText(favPlayers.get(position).getId().toString());
            holder.nombrePlayer.setText(favPlayers.get(position).getPlayer().getName().toString());

            holder.vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // FRAGMENTS DE PLAYER AL DE FAV PLAYER
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(PlayerDetailFragment.ARG_ITEM_ID, playerID);
                        PlayerDetailFragment fragment = new PlayerDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.player_detail_container, fragment)
                                .commit();

                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, PlayerDetailActivity.class);
                        // ese playerID es el del favPlayer
                        //Player p = FavouritePlayerManager.getInstance(getApplicationContext()).getFavouritePlayer(playerID).getPlayer();
                        intent.putExtra(PlayerDetailFragment.ARG_ITEM_ID, playerID);

                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
