package com.example.basketball.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.activities.master_detail.GameListActivity;
import com.example.basketball.controller.activities.master_detail.PlayerDetailActivity;
import com.example.basketball.controller.activities.master_detail.PlayerDetailFragment;
import com.example.basketball.controller.managers.PlayerCallback;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.Player;
import com.example.basketball.model.PlayerDTO;
import com.example.basketball.model.Position;

import java.util.Arrays;
import java.util.List;

public class PlayersByPosicion extends AppCompatActivity {
    public Spinner spinnerPosiciones;
    private RecyclerView recyclerView;
    String[] posiciones;
    Position posElegida;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_by_posicion);

        recyclerView = (RecyclerView) findViewById(R.id.playersList);
        assert recyclerView != null;

        posiciones = getNames(Position.class);
        spinnerPosiciones = (Spinner) findViewById(R.id.spinnerPosiciones);
        ArrayAdapter positionsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, posiciones);
        spinnerPosiciones.setAdapter(positionsAdapter);

        spinnerPosiciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (posiciones[position].equals("ALERO")) posElegida = Position.ALERO;
                else if (posiciones[position].equals("ALA")) posElegida = Position.ALA;
                else if (posiciones[position].equals("BASE")) posElegida = Position.BASE;
                else if (posiciones[position].equals("PIVOT")) posElegida = Position.PIVOT;

                // enviamos la petición que rellene la lista
                players = PlayerManager.getInstance(getApplicationContext()).getAllByPosition(posElegida);
                recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(players));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

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
            final String playerID = holder.playerSeleccionado.getId().toString();
            holder.idPlayer.setText(players.get(position).getId().toString());
            holder.nombrePlayer.setText(players.get(position).getName() + " " + players.get(position).getSurname());

            holder.vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, PlayerDetailActivity.class);
                    intent.putExtra(PlayerDetailFragment.ARG_ITEM_ID, playerID);

                    context.startActivity(intent);
                }
            });
        }
    }

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }

}
