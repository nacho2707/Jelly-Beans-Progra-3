package com.jellybeans;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextCodigo;
    EditText editTextContraseña;
    ConexionSQLiteHelper conn;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        conn = new ConexionSQLiteHelper(this, "usuarios", 1);

        sharedPreferences = getSharedPreferences("OneTimeLogin", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("loginValue", false)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextContraseña = findViewById(R.id.editTextContraseña);
    }


    public void doLogin(View view) {
        //buscar();
        if (validarDatosIngresados()) {
            if (validarSiExisteEnDB()) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                // No existe en la base de datos
                Toast.makeText(this, "Este usuario no existe, debe registrarse", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Mal escrito
            Toast.makeText(this, "Error en la escritura", Toast.LENGTH_SHORT).show();
        }
    }

    public void registro(View view) {
        if (validarDatosIngresados()) {
            if (validarSiExisteEnDB() == false) {
                registrarUsuarios();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                // Usuario ya existe
                Toast.makeText(this, "Este usuario ya fue registrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Mal escrito
        }


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginValue", true);
        editor.apply();
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean validarSiExisteEnDB() {
        return conn.validarSiUsuarioExiste(editTextCodigo.getText().toString(), editTextContraseña.getText().toString());
    }

    //este ,etodo deberia ir en un pantalla de registrar usuario
    public void registrarUsuarios() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String codigo = editTextCodigo.getText().toString();
        String contrasena = editTextContraseña.getText().toString();
        if (!codigo.isEmpty() && !contrasena.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("contrasena", contrasena);
            db.insert("usuarios", null, registro);
            db.close();
            editTextCodigo.setText("");
            editTextContraseña.setText("");
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        }

    }

    public void buscar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String codigo = editTextCodigo.getText().toString();
        if (!codigo.isEmpty()) {
            Cursor fila = db.rawQuery("select contrasena from ususarios where codigo = " + codigo, null);
            if (fila.moveToFirst()) {
                editTextContraseña.setText(fila.getString(0));
                db.close();
            } else {
                Toast.makeText(this, "Usuario no encontrado ", Toast.LENGTH_SHORT).show();
                db.close();
            }

        } else {
            Toast.makeText(this, "Introduce el codigo", Toast.LENGTH_SHORT).show();

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


    public boolean validarDatosIngresados() {
        editTextContraseña.setError(null);
        editTextCodigo.setError(null);

        String codigo = editTextCodigo.getText().toString();
        String contraseña = editTextContraseña.getText().toString();

        if (TextUtils.isEmpty(codigo)) {
            editTextCodigo.setError("Campo obligatorio");
            editTextCodigo.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(contraseña)) {
            editTextContraseña.setError("Campo obligatorio");
            editTextContraseña.requestFocus();
            return false;
        }

        if (codigo.length() != 5) {
            editTextCodigo.setError("Codigo de 5 digitos");
            editTextCodigo.requestFocus();
            return false;
        }

        return true;
    }
}
