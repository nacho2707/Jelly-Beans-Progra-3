package com.jellybeans;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AsientosBus extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asientos_bus);

    }
    public void onclick(View view){
        if (view.getId()==R.id.btnAceptar){

        }
    }
    private void Aceptar (){

    }
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Control de Asientos");
        }
    }
}
