package com.jellybeans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalleContactosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contactos);
        Bundle bundle = getIntent().getExtras();
        String nombre = bundle.getString("NOMBRECONTACTO");
        String apellido = bundle.getString("APELLIDOCONTACTO");
        String telefono = bundle.getString("TELEFONOCONTACTO");
        String correo = bundle.getString("CORREOCONTACTO");
    }
}
