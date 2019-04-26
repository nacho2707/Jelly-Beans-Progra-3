
package com.jellybeans;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToContacts(){
        Intent intent = new Intent(this, contactos.class);
        startActivity(intent);
    }

    public void goToMap(){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }


}
