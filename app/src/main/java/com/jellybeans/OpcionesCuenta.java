package com.jellybeans;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OpcionesCuenta extends AppCompatActivity {
    EditText editTextCod;
    EditText editTextContrasena;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_cuenta);
        editTextCod = findViewById(R.id.editTextCod);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        conn = new ConexionSQLiteHelper(this, "usuarios", 1);
    }
    public void eliminar(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String codigo = editTextCod.getText().toString();
        String contrasena = editTextContrasena.getText().toString();
        if (!codigo.isEmpty() && !contrasena.isEmpty()) {
            int cantidad = db.delete("usuarios", " codigo=? ", null);
            editTextCod.setText("");
            editTextContrasena.setText("");
            if (cantidad == 1) {
                Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "Debes escribir el codigo", Toast.LENGTH_SHORT).show();

        }


    }
    public void cambiarContrasena() {
        String codigo = editTextCod.getText().toString();
        String contrasena = editTextContrasena.getText().toString();

        boolean actualizacionCorrecta = conn.cambiarPassword(codigo, contrasena);

        if (actualizacionCorrecta) {
            Toast.makeText(this, "Contrasena cambiada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}
