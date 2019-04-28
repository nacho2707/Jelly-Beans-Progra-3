package com.jellybeans;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextCodigo;
    EditText editTextContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void doLogin(View view){
        editTextCodigo= (EditText) findViewById(R.id.editTextCodigo);
        editTextContraseña= (EditText) findViewById(R.id.editTextContraseña);
        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/

        validar();
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
            editTextCodigo.setError("Campo obligatorio");
            editTextCodigo.requestFocus();
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
