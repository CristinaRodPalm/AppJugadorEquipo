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
import com.example.basketball.controller.managers.TeamManager;
import com.example.basketball.model.Team;

public class TeamDetailFragment extends Fragment {

    public static String ARG_ITEM_ID = "item_id";
    private Team team;

    public TeamDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String id = getArguments().getString(ARG_ITEM_ID);
            team = TeamManager.getInstance(this.getContext()).getTeam(id);
            assert team != null;
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(team.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.team_detail, container, false);

        if (team != null) {
            //((TextView) rootView.findViewById(R.id.team_detail)).setText("City: " + team.getCity().toString());
            ((TextView) rootView.findViewById(R.id.ciudad)).setText(team.getCity().toString());
        }

        return rootView;
    }
}
