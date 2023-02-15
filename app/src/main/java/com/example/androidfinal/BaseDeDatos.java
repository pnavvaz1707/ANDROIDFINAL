package com.example.androidfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDatos extends SQLiteOpenHelper {

    public static final String CLIENTES_TABLA = "Clientes";
    public static final String CLIENTE_ID = "_id";
    public static final String NOMBRE = "nombre";
    public static final String TELEFONO = "telefono";
    public static final String UBICACION = "ubicacion";

    public static final String PEDIDOS_TABLA = "Pedidos";
    public static final String PEDIDO_ID = "_id";
    public static final String PIZZA = "pizza";
    public static final String CANTIDAD = "cantidad";
    public static final String PRECIO = "precio";
    public static final String ENTREGADA = "entregada";
    public static final String PEDIDOS_CLIENTE_ID = "cliente_id";

    private static final String CLIENTES_TABLA_CREAR = "CREATE TABLE "
            + CLIENTES_TABLA + " ("
            + CLIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NOMBRE + " TEXT NOT NULL, "
            + TELEFONO + " TEXT NOT NULL, "
            + UBICACION + " TEXT NOT NULL);";

    private static final String PEDIDOS_TABLA_CREAR = "CREATE TABLE "
            + PEDIDOS_TABLA + " ("
            + PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PIZZA + " TEXT NOT NULL, "
            + CANTIDAD + " INTEGER NOT NULL, "
            + PRECIO + " REAL NOT NULL, "
            + ENTREGADA + " INTEGER NOT NULL, "
            + PEDIDOS_CLIENTE_ID + " INTEGER,"
            + " FOREIGN KEY (" + PEDIDOS_CLIENTE_ID + ") REFERENCES " + CLIENTES_TABLA + "(" + CLIENTE_ID + "));";

    private static final String CLIENTES_TABLA_BORRAR = "DROP TABLE IF EXISTS " + CLIENTES_TABLA;
    private static final String PEDIDOS_TABLA_BORRAR = "DROP TABLE IF EXISTS " + PEDIDOS_TABLA;

    // Constructor de la clase. Podr�amos eliminar los par�metros
    // menos el contexto
    public BaseDeDatos(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos la tabla por SQL. Podr�a ya existir la BD
        db.execSQL(CLIENTES_TABLA_CREAR);
        db.execSQL(PEDIDOS_TABLA_CREAR);
    }

    @Override
    // S�lo se ejecuta si las versiones son distintas
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versi�n anterior de la tabla
        db.execSQL(CLIENTES_TABLA_BORRAR);
        db.execSQL(PEDIDOS_TABLA_BORRAR);
        // Volvemos a crear la tabla
        db.execSQL(CLIENTES_TABLA_CREAR);
        db.execSQL(PEDIDOS_TABLA_CREAR);
    }

}

