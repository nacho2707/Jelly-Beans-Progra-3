package com.jellybeans;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.Toast;

public class MensajeAlerta extends AppCompatActivity {
    Button aceptar;

    GridLayout gridLayout;

    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asientos_bus);


        gridLayout = findViewById(R.id.gridLayoutBuses);

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked) {
                    contador ++;
                } else {
                    contador --;
                }
            }
        };

        for(int i = 0; i<40; i++) {
            CheckBox checkBox = new CheckBox(this);

            GridLayout.LayoutParams params= new GridLayout.LayoutParams(GridLayout.spec(
                    GridLayout.UNDEFINED,GridLayout.FILL,1f),
                    GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL,1f));
            params.height = 0;
            params.width = 0;

            checkBox.setText("A" + i);
            checkBox.setOnCheckedChangeListener(onCheckedChangeListener);

            checkBox.setLayoutParams(params);
            gridLayout.addView(checkBox);
        }


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
