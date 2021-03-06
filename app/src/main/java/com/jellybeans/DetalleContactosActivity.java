package com.jellybeans;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleContactosActivity extends AppCompatActivity {
    TextView tvNombre,tvApellido,tvTelefono,tvCorreo;
    RelativeLayout relativeLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_detalle_contactos);
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contactos);
        String nombre = getIntent().getStringExtra("NOMBRECONTACTO");
        String apellido = getIntent().getStringExtra("APELLIDOCONTACTO");
        final int telefono = getIntent().getIntExtra("TELEFONOCONTACTO", 0);
        String correo = getIntent().getStringExtra("CORREOCONTACTO");

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvApellido = (TextView) findViewById(R.id.tvApellido);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvCorreo = (TextView) findViewById(R.id.tvCorreo);
        relativeLayout = (RelativeLayout) findViewById(R.id.rlCall);
        button = (Button)findViewById(R.id.btnCall);

        tvNombre.setText(nombre);
        tvApellido.setText(apellido);
        tvTelefono.setText(String.valueOf(telefono));
        tvCorreo.setText(correo);
        relativeLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono));
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED);
                   //TODOConsider calling
                   // ActivityCompat#requestPermissions
                   // here to request the missing permissions, and then overriding
                   // public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grandResults)
                   //
                   // to handle the case where the user grands the permission. See the documentation
                   //for ActivityCompat#requestPermissions for more details.
                Toast.makeText(DetalleContactosActivity.this,"Falta activar el permiso de llamadas", Toast.LENGTH_SHORT).show();
                startActivity(intent);


            }
            //startDetalleContactosActivity(intent);

        });
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Datos Personales");
        }
    }
}
