package com.jellybeans;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MensajeAlerta extends AppCompatActivity {
    Button aceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_alerta);
        aceptar =(Button) findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MensajeAlerta.this);

                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("Aceptar");
                builder.setMessage("este es mi primer alert dialog");

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }
}
