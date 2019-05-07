package com.jellybeans;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jellybeans.utilidades.Utilidades;

public class LoginActivity extends AppCompatActivity {

    EditText editTextCodigo;
    EditText editTextContraseña;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this, "bdUsuarios",1);

        sharedPreferences=getSharedPreferences("OneTimeLogin",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("loginValue",false)==true){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{

        }
    }



    public void doLogin(View view){
        editTextCodigo= (EditText) findViewById(R.id.editTextCodigo);
        editTextContraseña= (EditText) findViewById(R.id.editTextContraseña);

        //revisar deberia ser en un onClick
        registrarUsuarios();

        validar();
    }
    //este ,etodo deberia ir en un pantalla de registrar usuario
    public void registrarUsuarios(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdUsuarios",1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_CODIGO,editTextCodigo.getText().toString());
        values.put(Utilidades.CAMPO_CONTRASENA,editTextContraseña.getText().toString());
        Long idResultante = db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_CODIGO,values);
        Toast.makeText(getApplicationContext(),"Codigo Registro: " + idResultante, Toast.LENGTH_SHORT).show();
    }

    public void validar(){
        editTextContraseña.setError(null);
        editTextCodigo.setError(null);

        String codigo= editTextCodigo.getText().toString();
        String contraseña= editTextContraseña.getText().toString();

        if(TextUtils.isEmpty(codigo)){
            editTextCodigo.setError("Campo obligatorio");
            editTextCodigo.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(contraseña)){
            editTextContraseña.setError("Campo obligatorio");
            editTextContraseña.requestFocus();
            return;
        }



        if(codigo.length() != 5 ){
            editTextCodigo.setError("Codigo de 5 digitos");
            editTextCodigo.requestFocus();
            return;
        }

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }




}
