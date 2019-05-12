package com.jellybeans;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OpcionesCuenta extends AppCompatActivity {
    EditText editTextCodigo;
    EditText editTextContraseña;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_cuenta);
        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        conn = new ConexionSQLiteHelper(this, "usuarios", 1);
    }
    public void eliminar(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String codigo = editTextCodigo.getText().toString();
        String contrasena = editTextContraseña.getText().toString();
        if (!codigo.isEmpty() && !contrasena.isEmpty()) {
            int cantidad = db.delete("usuarios", "codigo=", null);
            editTextCodigo.setText("");
            editTextContraseña.setText("");
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
        String codigo = editTextCodigo.getText().toString();
        String contrasena = editTextContraseña.getText().toString();

        boolean actualizacionCorrecta = conn.cambiarPassword(codigo, contrasena);

        if (actualizacionCorrecta) {
            Toast.makeText(this, "Contrasena cambiada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}
