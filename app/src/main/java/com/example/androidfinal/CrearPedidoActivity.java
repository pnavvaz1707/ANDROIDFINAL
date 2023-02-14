package com.example.androidfinal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CrearPedidoActivity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private Spinner spinnerPizzas;
    private EditText etIdClientePedido;
    private EditText etCantidadPizza;
    private Button btnCancelar;
    private Button btnCrear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_pedido_window);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        db = ClientesActivity.db;

        spinnerPizzas = (Spinner) findViewById(R.id.spinnerPizzas);
        etIdClientePedido = (EditText) findViewById(R.id.etIdClientePedido);
        etCantidadPizza = (EditText) findViewById(R.id.etCantidadPizza);
        btnCrear = (Button) findViewById(R.id.btnCrearClienteOk);

        btnCrear.setOnClickListener(this);

        spinnerPizzas.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, TiposPizza.values()));
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
        ContentValues insercion = new ContentValues();

        TiposPizza pizzaSel = TiposPizza.valueOf(spinnerPizzas.getSelectedItem().toString());
        /*for (TiposPizza pizza : TiposPizza.values()) {
            if (spinnerPizzas.getSelectedItem().toString().equals(pizza.name())) {
                nombrePizza = pizza.name();
                precioPizza = pizza.getPrecio();
            }
        }*/

        insercion.put(BaseDeDatos.PIZZA, pizzaSel.name());
        insercion.put(BaseDeDatos.PRECIO, pizzaSel.getPrecio());
        insercion.put(BaseDeDatos.CANTIDAD, Integer.parseInt(etCantidadPizza.getText().toString()));
        insercion.put(BaseDeDatos.PEDIDOS_CLIENTE_ID, Integer.parseInt(etIdClientePedido.getText().toString()));

        System.out.println("PizzaSP --> " + spinnerPizzas.getSelectedItem().toString());
        System.out.println("Pizza --> " + insercion.get(BaseDeDatos.PIZZA));
        System.out.println("Precio --> " + insercion.get(BaseDeDatos.PRECIO));
        System.out.println("Cantidad --> " + insercion.get(BaseDeDatos.CANTIDAD));
        System.out.println("Id cliente --> " + insercion.get(BaseDeDatos.PEDIDOS_CLIENTE_ID));

        db.insert(BaseDeDatos.PEDIDOS_TABLA, null, insercion);
    }
}
