package com.example.androidfinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PedidosActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCrear;
    private Button btnEliminar;
    private Button btnActualizar;
    private ListView lstPedidos;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidos_window);

        BaseDeDatos baseDeDatos = new BaseDeDatos(this, "BDPizzeria", null, 2);
        db = baseDeDatos.getWritableDatabase();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lstPedidos = (ListView) findViewById(R.id.lstPedidos);

        btnCrear = (Button) findViewById(R.id.btnCrearPedido);
        btnEliminar = (Button) findViewById(R.id.btnEliminarPedido);
        btnActualizar = (Button) findViewById(R.id.btnActualizarPedidos);

        btnCrear.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnCrear.getId()) {
            startActivity(new Intent(this, CrearPedidoActivity.class));

        } else if (v.getId() == btnEliminar.getId()) {


        } else if (v.getId() == btnActualizar.getId()) {
            Cursor c1 = db.rawQuery("SELECT * FROM " + BaseDeDatos.PEDIDOS_TABLA, null);

            if (c1.moveToFirst()) {
                //Recorremos el cursor hasta que no haya m�s registros
                do {
                    System.out.println("----> " + c1.getInt(0) + " " + c1.getString(1) + " " + c1.getString(2) + " " + c1.getString(3));
                    System.out.println("/////////////////////////");
                } while (c1.moveToNext());
            } else {
                System.out.println("No hay nada");
            }

            String[] nombresColumnas = new String[]{BaseDeDatos.PEDIDO_ID, BaseDeDatos.PIZZA, BaseDeDatos.PRECIO, BaseDeDatos.PRECIO};
            int[] referencias = new int[]{R.id.tvIdPedidoCliente, R.id.tvNombrePizza, R.id.tvCantidadPizza, R.id.tvPrecioPizza};

            CustomAdaptadorPedidos customAdaptadorPedidos = new CustomAdaptadorPedidos(this, R.layout.list_pedidos, c1, nombresColumnas, referencias, 0);
            lstPedidos.setAdapter(customAdaptadorPedidos);
        } else {

        }
    }
}
