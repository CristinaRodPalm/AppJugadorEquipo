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
import com.example.basketball.controller.managers.GameManager;
import com.example.basketball.model.Game;

public class GameDetailFragment extends Fragment {

    public static String ARG_ITEM_ID = "item_id";
    private Game game;
    public TextView localEq, visitanteEq, localPunt, visitantePunt;

    public GameDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String id = getArguments().getString(ARG_ITEM_ID);
            game = GameManager.getInstance(this.getContext()).getGame(id);
            assert game != null;
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(game.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.game_detail, container, false);

        if (game != null) {

            localEq = (TextView) rootView.findViewById(R.id.localEq);
            visitanteEq = (TextView) rootView.findViewById(R.id.visitanteEq);
            localPunt = (TextView) rootView.findViewById(R.id.localPunt);
            visitantePunt = (TextView) rootView.findViewById(R.id.visitantePunt);

            //((TextView) rootView.findViewById(R.id.game_detail)).setText("Nombre: " + game.getName().toString());

            localEq.setText(game.getGameLocalTeam().getName().toString());
            visitanteEq.setText(game.getGameVisitorTeam().getName().toString());
            localPunt.setText(game.getLocalScore().toString());
            visitantePunt.setText(game.getVisitorScore().toString());
        }

        return rootView;
    }
}
