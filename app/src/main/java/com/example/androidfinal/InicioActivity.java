package com.example.androidfinal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClientes;
    private Button btnPedidos;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_window);

        btnClientes = (Button) findViewById(R.id.btnClientes);
        btnPedidos = (Button) findViewById(R.id.btnPedidos);

        btnClientes.setOnClickListener(this);
        btnPedidos.setOnClickListener(this);

        BaseDeDatos baseDeDatos = new BaseDeDatos(this, getString(R.string.nombre_bd), null, 3);
        db = baseDeDatos.getWritableDatabase();

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(InicioActivity.this, R.string.hilo_temp_txt, Toast.LENGTH_SHORT).show();
                handler.postDelayed(this, 60*1000); // 5 minutos
            }
        };
        handler.postDelayed(runnable, 60*1000); // 5 minutos
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnClientes.getId()) {
            startActivity(new Intent(this, ClientesActivity.class));
        } else if (v.getId() == btnPedidos.getId()) {
            startActivity(new Intent(this, PedidosActivity.class));
        }
    }

}
