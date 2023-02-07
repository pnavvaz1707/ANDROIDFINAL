package com.example.androidfinal;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PedidosActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCrear;
    private Button btnEliminar;
    private Button btnModificar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidos_window);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnCrear = (Button) findViewById(R.id.btnCrearPedido);
        btnEliminar = (Button) findViewById(R.id.btnEliminarPedido);
        btnModificar = (Button) findViewById(R.id.btnModificarPedido);

        btnCrear.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnModificar.setOnClickListener(this);
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

        } else if (v.getId() == btnModificar.getId()) {

        } else if (v.getId() == btnEliminar.getId()) {

        }
    }
}
