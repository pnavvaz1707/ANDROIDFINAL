package com.example.androidfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClientes;
    private Button btnPedidos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_window);

        btnClientes = (Button) findViewById(R.id.btnClientes);
        btnPedidos = (Button) findViewById(R.id.btnPedidos);

        btnClientes.setOnClickListener(this);
        btnPedidos.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == btnClientes.getId()) {
            /*
            Button btn = (Button) view;
            Intent i = new Intent(getApplicationContext(), VentanaManual.class);

            Bundle bundle = new Bundle();
            bundle.putString("Titulo", btn.getText().toString());
            bundle.putInt("idTitulo", Integer.parseInt(btn.getTag().toString()));

            i.putExtras(bundle);
            startActivity(i);
             */
            startActivity(new Intent(this,ClientesActivity.class));
        } else if (v.getId() == btnPedidos.getId()) {
            startActivity(new Intent(this,PedidosActivity.class));
        }
    }

}
