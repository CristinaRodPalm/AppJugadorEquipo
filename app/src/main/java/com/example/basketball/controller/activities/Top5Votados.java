package com.example.basketball.controller.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.FavouritePlayerCallback;
import com.example.basketball.controller.managers.FavouritePlayerManager;
import com.example.basketball.model.FavouritePlayer;
import com.example.basketball.model.PlayerDTO;

import java.util.List;

public class Top5Votados extends AppCompatActivity implements FavouritePlayerCallback{

    private RecyclerView recyclerView;
    private List<PlayerDTO> top5Players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topfive_activity);

        recyclerView = (RecyclerView) findViewById(R.id.topFiveList);
        assert recyclerView != null;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        FavouritePlayerManager.getInstance(this.getApplicationContext()).getTop5Players(Top5Votados.this);
    }

    @Override
    public void onSuccess(List<FavouritePlayer> playerList) { }

    @Override
    public void onFailure(Throwable t) { }

    @Override
    public void onSuccess(FavouritePlayer favouritePlayer) { }

    @Override
    public void onSuccess() { }

    // funcionará este
    @Override
    public void onSuccessDTO(List<PlayerDTO> playerDTOList) {
        top5Players = playerDTOList;
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(top5Players));
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(top5Players));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private List<PlayerDTO> top5Players;

        public SimpleItemRecyclerViewAdapter(List<PlayerDTO> items) {
            top5Players = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.topfive_list_content, parent, false);
            return new ViewHolder(view);
        }

        // Para saber cuántos teams recibimos
        @Override
        public int getItemCount() {
            return top5Players.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View vista;
            public final TextView positionFav, nombreFav, numVotosFav;
            public PlayerDTO topPlayer;

            public ViewHolder(View view) {
                super(view);
                vista = view;
                positionFav = (TextView) view.findViewById(R.id.posicion);
                nombreFav = (TextView) view.findViewById(R.id.nombre);
                numVotosFav = (TextView) view.findViewById(R.id.numVotos);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + nombreFav.getText() + "'";
            }
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.topPlayer = top5Players.get(position);
            holder.positionFav.setText(String.valueOf(position+1));
            holder.numVotosFav.setText(top5Players.get(position).getNumFavs().toString());
            String name = top5Players.get(position).getPlayer().getName().toString();
            String surname = top5Players.get(position).getPlayer().getSurname().toString();
            holder.nombreFav.setText(name+" "+surname);
        }
    }
}
