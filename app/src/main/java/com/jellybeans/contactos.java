package com.jellybeans;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class contactos extends AppCompatActivity {
    ListView lvContactos;
    ArrayList<Contacto> contactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        lvContactos = (ListView) findViewById(R.id.lvcontactos);
        contactos = new ArrayList<>();
        contactos.add( new Contacto("Nicolas", "Lupa", "donnico69@gmail.com", 76233078));
        contactos.add( new Contacto("Carlos", "Quispe", "donnico69@gmail.com", 76233078));
        contactos.add(new Contacto("German", "Garcia", "donnico69@gmail.com", 76233078));
        contactos.add(new Contacto("Marco", "Mamani", "donnico69@gmail.com", 76233078));
        ArrayList<String> nombreContactos = new ArrayList<>();
        for (Contacto contacto: contactos){
            nombreContactos.add(contacto.getNombre() + " " + contacto.getApellido());
        }
        lvContactos.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nombreContactos));
        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(contactos.this, DetalleContactosActivity.class);
                intent.putExtra("NOMBRECONTACTO", contactos.get(position).getNombre());
                intent.putExtra("APELLIDOCONTACTO", contactos.get(position).getApellido());
                intent.putExtra("TELEFONOCONTACTO", contactos.get(position).getTelefono());
                intent.putExtra("CORREOCONTACTO", contactos.get(position).getCorreo());
                startActivity(intent);
            }
        });


    }
}
