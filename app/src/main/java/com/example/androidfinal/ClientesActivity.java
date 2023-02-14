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

public class ClientesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCrear;
    private Button btnActualizar;
    private ListView lstClientes;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes_window);

        BaseDeDatos baseDeDatos = new BaseDeDatos(this, "BDPizzeria", null, 2);
        db = baseDeDatos.getWritableDatabase();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lstClientes = (ListView) findViewById(R.id.lstClientes);

        btnCrear = (Button) findViewById(R.id.btnCrearCliente);
        btnActualizar = (Button) findViewById(R.id.btnActualizarClientes);

        btnCrear.setOnClickListener(this);
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
            startActivity(new Intent(this, CrearClienteActivity.class));

        } else if (v.getId() == btnActualizar.getId()) {
            Cursor c1 = db.rawQuery("SELECT * FROM " + BaseDeDatos.CLIENTES_TABLA, null);

            if (c1.moveToFirst()) {
                //Recorremos el cursor hasta que no haya m�s registros
                do {
                    System.out.println("----> " + c1.getInt(0) + " " + c1.getString(1) + " " + c1.getString(2) + " " + c1.getString(3));
                    System.out.println("/////////////////////////");
                } while (c1.moveToNext());
            } else {
                System.out.println("No hay nada");
            }

            String[] nombresColumnas = new String[]{BaseDeDatos.CLIENTE_ID, BaseDeDatos.NOMBRE, BaseDeDatos.TELEFONO, BaseDeDatos.UBICACION};
            int[] referencias = new int[]{R.id.tvId, R.id.tvNombre, R.id.tvTel, R.id.tvUbicacion};

            CustomAdaptadorClientes customAdaptadorClientes = new CustomAdaptadorClientes(this, R.layout.list_clientes, c1, nombresColumnas, referencias, 0);
            lstClientes.setAdapter(customAdaptadorClientes);

        }
    }
}
