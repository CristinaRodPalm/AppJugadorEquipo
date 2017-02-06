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
                appBarLayout.setTitle(player.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (player != null) {
            ((TextView) rootView.findViewById(R.id.player_detail)).setText("Baskets: " + player.getBaskets().toString());
        }

        return rootView;
    }
}
