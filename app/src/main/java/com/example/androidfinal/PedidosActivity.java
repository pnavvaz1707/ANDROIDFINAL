package com.example.androidfinal;

import static com.example.androidfinal.InicioActivity.db;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PedidosActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCrear;
    private Button btnActualizar;
    private ListView lstPedidos;

    /**
     * Método que se ejecuta al iniciar la activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedidos_window);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lstPedidos = (ListView) findViewById(R.id.lstPedidos);

        btnCrear = (Button) findViewById(R.id.btnCrearPedido);
        btnActualizar = (Button) findViewById(R.id.btnActualizarPedidos);

        btnCrear.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);

        actualizar();
    }

    /**
     * Método para asignarle una función de volver hacia atrás a la flecha de arriba izquierda
     * @param item (Flecha)
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método que se ejecuta al hacer click en un botón
     * @param v (Botón pulsado)
     */
    @Override
    public void onClick(View v) {
        //Si es el botón crear iniciamos la actividad de crear pedido
        if (v.getId() == btnCrear.getId()) {
            startActivity(new Intent(this, CrearPedidoActivity.class));

        }//Si es el botón actualizar actualizamos los datos del list view
        else if (v.getId() == btnActualizar.getId()) {
            actualizar();
        }
    }

    /**
     * Método para actualizar los datos del listview
     */
    private void actualizar() {
        Cursor c1 = db.rawQuery("SELECT * FROM " + BaseDeDatos.PEDIDOS_TABLA, null);

        String[] nombresColumnas = new String[]{BaseDeDatos.PEDIDO_ID, BaseDeDatos.PIZZA, BaseDeDatos.CANTIDAD, BaseDeDatos.PRECIO};
        int[] referencias = new int[]{R.id.tvIdPedido, R.id.tvNombrePizza, R.id.tvCantidadPizza, R.id.tvPrecioPizza};

        CustomAdaptadorPedidos customAdaptadorPedidos = new CustomAdaptadorPedidos(this, R.layout.list_pedidos, c1, nombresColumnas, referencias, 0);
        lstPedidos.setAdapter(customAdaptadorPedidos);

        Toast.makeText(this, getString(R.string.lista_pedidos_actualizada), Toast.LENGTH_SHORT).show();
    }
}
