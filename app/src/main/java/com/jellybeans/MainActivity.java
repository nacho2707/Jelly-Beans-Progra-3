
package com.jellybeans;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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
