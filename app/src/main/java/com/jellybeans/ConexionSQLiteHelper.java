package com.jellybeans;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL("create table usuarios(_id integer PRIMARY KEY AUTOINCREMENT ,codigo text UNIQUE, contrasena text, succesful BOOLEAN)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);

    }

    public boolean validarSiUsuarioExiste(String codigo, String password) {
        try {
            Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM usuarios WHERE codigo = ? AND contrasena = ?"
                    , new String[]{codigo, password});
            if (cursor.moveToNext() == false) {
                cursor.close();
                return false;
            } else {
                cursor.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cambiarPassword(String codigo, String nuevoPassword) {
        SQLiteDatabase db = getWritableDatabase();
        if (!codigo.isEmpty()) {
            ContentValues valoresAActualizar = new ContentValues();
            valoresAActualizar.put("contrasena", nuevoPassword);
            int cantidad = db.update("usuarios", valoresAActualizar, "codigo=?", new String[]{codigo});
            db.close();
            if (cantidad == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


}

