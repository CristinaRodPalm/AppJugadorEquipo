package com.example.basketball.controller.activities.master_detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.managers.PlayerManager;
import com.example.basketball.model.Player;

public class PlayerDetailFragment extends Fragment {

    public static String ARG_ITEM_ID = "item_id";
    private Player player;
    public TextView canastas, asistencias, rebotes, posicion, equipo;

    public PlayerDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String id = getArguments().getString(ARG_ITEM_ID);
            player = PlayerManager.getInstance(this.getContext()).getPlayer(id);
            assert player != null;
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(player.getName()+" "+player.getSurname());
            }
        }

        //FavouritePlayerManager.getInstance(getContext()).getFavPlayerExists(PlayerDetailFragment.this, player.getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_detail, container, false);

        canastas = (TextView) rootView.findViewById(R.id.canastas);
        asistencias = (TextView) rootView.findViewById(R.id.asistencias);
        rebotes = (TextView) rootView.findViewById(R.id.rebotes);
        posicion = (TextView) rootView.findViewById(R.id.posicion);
        equipo = (TextView) rootView.findViewById(R.id.equipo);

        if (player != null) {
            canastas.setText(player.getNumBaskets().toString());
            asistencias.setText(player.getNumAssists().toString());
            rebotes.setText(player.getNumRebounds().toString());
            posicion.setText(player.getPosition().toString());
            equipo.setText(player.getTeam().getName().toString());
            /*String text = "Baskets: " + player.getNumBaskets().toString() +
                    "\nAssists: " + player.getNumAssists().toString()+
                    "\nRebounds: " + player.getNumRebounds().toString()+
                    "\nPosition: " + player.getPosition().toString().toLowerCase()+
                    "\nTeam: " + player.getTeam().getName();
            ((TextView) rootView.findViewById(R.id.player_detail)).
                    setText(text);*/
        }

        return rootView;
    }
}
