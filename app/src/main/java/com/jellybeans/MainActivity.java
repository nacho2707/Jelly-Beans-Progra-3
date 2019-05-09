
package com.jellybeans;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToContacts(View view){
        Intent intent = new Intent(this, contactos.class);
        startActivity(intent);
    }

    public void goToMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void goToDisponibilidadAsientos(View view){
        Intent intent = new Intent(this, MensajeAlerta.class);
        startActivity(intent);
    }

    public void goToRutasHorarios(View view){
        Intent intent = new Intent(this,RutasBus.class);
        startActivity(intent);
    }

    public void goToLogOut(View view){
        boolean value= false;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginValue", value);
        editor.apply();
        editor.commit();

        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }




}
