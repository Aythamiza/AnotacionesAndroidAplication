package com.example.friki.anotaciones2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Anotaciones";
    private static final int SCHEMA_VERSION = 1;


    public dbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table datos (_ID integer Primary Key autoincrement, Categoria text , Anotacion text , Fecha text )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void insertarregistro(String cat, String anotacion, String fecha) {
        ContentValues valores = new ContentValues();
        valores.put("Categoria", cat);
        valores.put("Anotacion", anotacion);
        valores.put("Fecha", fecha);
        this.getWritableDatabase().insert("datos", null, valores);
    }

    public ArrayList llernarLista() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String q = "select * from datos order by _ID desc";
        Cursor reg = database.rawQuery(q, null);
        if (reg.moveToFirst()) {
            do {
                lista.add("Categoria:" + " " + reg.getString(1) +"\n"+ "Fecha Guardada:" +
                        " " + reg.getString(3)+"\n" + "Anotacion:" + " " + "" + reg.getString(2) );
            } while (reg.moveToNext());
        }

        return lista;
    }
    public void abrir(){
        this.getWritableDatabase();
    }

    public  void leer(){this.getReadableDatabase();}

    // metodo para cerrar la base de datos
    public void cerrar(){
        this.close();
    }



    public ArrayList buscarPorCategoria(String categoria) {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String q = "select * from datos where Categoria ='" + categoria + "'";
        Cursor reg = database.rawQuery(q, null);
        if (reg.moveToFirst()) {
            do {
                lista.add("Categoria:" + " " + reg.getString(1) + "\n" + "Anotacion:" +
                        " " + reg.getString(2) + "\n" + "Fecha Guardada" + " " + reg.getString(3));
            } while (reg.moveToNext());
        }
        return lista;
    }


    public ArrayList buscarPorfecha(String fecha) {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String q = "select * from datos where Fecha ='" + fecha + "'";
        Cursor reg = database.rawQuery(q, null);
        if (reg.moveToFirst()) {
            do {
                lista.add("Categoria:" + " " + reg.getString(1) + "\n" + "Anotacion:" +
                        " " + reg.getString(2) + "\n" + "Fecha Guardada" + " " + reg.getString(3));
            } while (reg.moveToNext());
        }
        return lista;
    }

}



