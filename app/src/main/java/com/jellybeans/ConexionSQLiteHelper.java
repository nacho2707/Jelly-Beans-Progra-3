package com.jellybeans;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jellybeans.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper{


    public ConexionSQLiteHelper(Context context, String name, int version) {
        super(context, name, null , version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      //  db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL("create table usuarios(codigo text primary key, contrasena text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);

    }
}
