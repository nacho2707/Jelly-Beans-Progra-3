package com.jellybeans;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jellybeans.utilidades.Utilidades;

public class LoginActivity extends AppCompatActivity {

    EditText editTextCodigo;
    EditText editTextContraseña;
    ConexionSQLiteHelper conn;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        conn = new ConexionSQLiteHelper(this, "bdUsuarios", 1);

        sharedPreferences = getSharedPreferences("OneTimeLogin", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("loginValue", false)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextContraseña = findViewById(R.id.editTextContraseña);
    }


    public void doLogin(View view) {
        if (validar()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {

        }
    }

    public void registro(View view) {
        if (validar()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {

        }
        registrarUsuarios();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginValue", true);
        editor.apply();
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //este ,etodo deberia ir en un pantalla de registrar usuario
    public void registrarUsuarios() {
        /*SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_CODIGO, editTextCodigo.getText().toString());
        values.put(Utilidades.CAMPO_CONTRASENA, editTextContraseña.getText().toString());
        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_CODIGO, values);
        Toast.makeText(getApplicationContext(), "Codigo Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        db.close();*/

        ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this,"base",1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codigo = editTextCodigo.getText().toString();
        String contrasena = editTextContraseña.getText().toString();
        if(!codigo.isEmpty() && !contrasena.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("contrasena", contrasena);
            db.insert("usuarios",null, registro);
            db.close();
            editTextCodigo.setText("");
            editTextContraseña.setText("");
            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();

        }

    }

    public boolean validar() {
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

        return validarDatos();
    }
    // Select de la base de datos con un filtro
    public boolean validarDatos() {
        try {
            Cursor cursor =  conn.getReadableDatabase().rawQuery("SELECT * FROM TABLA_USUARIO WHERE codigo = ? AND contrasena = ?"
                    , new String[]{editTextCodigo.getText().toString(),editTextContraseña.getText().toString()});
            if (cursor.moveToNext()== false) {
                Toast.makeText(this, "Este usuario no existe", Toast.LENGTH_LONG).show();
                return false;
            }
            cursor.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
