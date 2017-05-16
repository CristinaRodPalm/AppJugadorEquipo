package com.example.basketball.controller.activities.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import com.example.basketball.R;
import com.example.basketball.controller.activities.master_detail.PlayerListActivity;
import com.example.basketball.controller.activities.master_detail.TeamListActivity;
import com.example.basketball.controller.managers.UserLoginManager;
import com.example.basketball.model.UserToken;

public class MainActivity extends TabActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, PlayerListActivity.class);
        spec = tabHost.newTabSpec("Players").setIndicator("Players")
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, TeamListActivity.class);
        spec = tabHost.newTabSpec("Teams").setIndicator("Teams")
                .setContent(intent);
        tabHost.addTab(spec);

    }

    @Override
    protected void onResume() {
        super.onResume();

        UserToken userToken = UserLoginManager.getInstance(this.getApplicationContext()).getUserToken();

        if (userToken == null) {
            Log.e("MainActivity->", "onResume ERROR: userToken is NULL");
        }
    }

}
