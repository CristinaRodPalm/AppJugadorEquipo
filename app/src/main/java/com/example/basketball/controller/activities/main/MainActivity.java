package com.example.basketball.controller.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.basketball.R;
import com.example.basketball.controller.activities.master_detail.PlayerListActivity;
import com.example.basketball.controller.activities.master_detail.TeamListActivity;
import com.example.basketball.controller.managers.UserLoginManager;
import com.example.basketball.model.UserToken;

public class MainActivity extends AppCompatActivity {

    private Button getAllPlayers, getAllTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllPlayers = (Button) findViewById(R.id.getAllPlayers);
        getAllTeams = (Button) findViewById(R.id.getAllTeams);
    }

    @Override
    protected void onResume() {
        super.onResume();

        UserToken userToken = UserLoginManager.getInstance(this.getApplicationContext()).getUserToken();

        if(userToken == null){
            Log.e("MainActivity->", "onResume ERROR: userToken is NULL");
        }

        getAllPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getAllPlayers = new Intent(MainActivity.this, PlayerListActivity.class);
                startActivity(getAllPlayers);
            }
        });
        getAllTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getAllTeams = new Intent(MainActivity.this, TeamListActivity.class);
                startActivity(getAllTeams);

            }
        });
    }
}
