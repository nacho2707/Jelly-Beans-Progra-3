package com.jellybeans;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MapActivity extends AppCompatActivity {

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_map);
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Mapa");
        }

    }
}
