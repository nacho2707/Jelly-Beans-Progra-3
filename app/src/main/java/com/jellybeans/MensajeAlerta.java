package com.jellybeans;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MensajeAlerta extends AppCompatActivity {
    Button aceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asientos_bus);
        aceptar =(Button) findViewById(R.id.btnAceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MensajeAlerta.this);

                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("Aceptar");
                builder.setMessage(" Desea confirmar su reserva?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MensajeAlerta.this,"Gracias por reservar con anticipacion ",Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MensajeAlerta.this,"Su reserva ha sido cancelada ",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }
}
