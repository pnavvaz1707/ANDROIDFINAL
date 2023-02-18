package com.example.androidfinal;

import static com.example.androidfinal.InicioActivity.db;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CrearPedidoActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinnerPizzas;
    private EditText etIdClientePedido;
    private EditText etCantidadPizza;
    private Button btnCrear;

    /**
     * Método que se ejecuta al iniciar la activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_pedido_window);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        spinnerPizzas = (Spinner) findViewById(R.id.spinnerPizzas);
        etIdClientePedido = (EditText) findViewById(R.id.etIdClientePedido);
        etCantidadPizza = (EditText) findViewById(R.id.etCantidadPizza);
        btnCrear = (Button) findViewById(R.id.btnCrearPedidoOk);

        btnCrear.setOnClickListener(this);

        String[] pizzas = getResources().getStringArray(R.array.pizzas);
        spinnerPizzas.setAdapter(new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, pizzas));
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
        //Si es el botón crear, comprobamos que los datos estén bien introducidos y creamos el pedido en la base de datos
        if (v.getId() == btnCrear.getId()) {
            if (etIdClientePedido.getText().toString().isEmpty() || etCantidadPizza.getText().toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.error_campos_vacio), Toast.LENGTH_SHORT).show();
            } else {
                try {
                    if (Integer.parseInt(etCantidadPizza.getText().toString()) == 0) {
                        Toast.makeText(this, getString(R.string.error_0_pizzas), Toast.LENGTH_SHORT).show();
                    } else {
                        Cursor c = db.rawQuery("SELECT " + BaseDeDatos.CLIENTE_ID + " FROM " + BaseDeDatos.CLIENTES_TABLA + " WHERE " + BaseDeDatos.CLIENTE_ID + " = " + Integer.parseInt(etIdClientePedido.getText().toString()), null);
                        if (c.moveToNext()) {
                            ContentValues insercion = new ContentValues();

                            insercion.put(BaseDeDatos.PIZZA, spinnerPizzas.getSelectedItem().toString());
                            insercion.put(BaseDeDatos.CANTIDAD, Integer.parseInt(etCantidadPizza.getText().toString()));
                            insercion.put(BaseDeDatos.PRECIO, 10);
                            insercion.put(BaseDeDatos.ENTREGADA, 0);
                            insercion.put(BaseDeDatos.PEDIDOS_CLIENTE_ID, Integer.parseInt(etIdClientePedido.getText().toString()));

                            db.insert(BaseDeDatos.PEDIDOS_TABLA, null, insercion);

                            Toast.makeText(this, getString(R.string.pedido_creado, spinnerPizzas.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();

                            etIdClientePedido.setText("");
                            etCantidadPizza.setText("");
                        } else {
                            Toast.makeText(this, getString(R.string.cliente_no_existe), Toast.LENGTH_SHORT).show();
                        }
                        c.close();
                    }
                }catch (Exception e){
                    Toast.makeText(this, getString(R.string.error_cantidad_pizzas), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
