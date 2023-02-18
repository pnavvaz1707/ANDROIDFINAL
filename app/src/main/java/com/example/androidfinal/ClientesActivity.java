package com.example.androidfinal;

import static com.example.androidfinal.InicioActivity.db;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ClientesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCrear;
    private Button btnActualizar;
    private Button btnFiltrar;
    private EditText etFiltrarTel;
    private ListView lstClientes;

    /**
     * Método que se ejecuta al iniciar la activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes_window);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lstClientes = (ListView) findViewById(R.id.lstClientes);

        btnCrear = (Button) findViewById(R.id.btnCrearCliente);
        btnActualizar = (Button) findViewById(R.id.btnActualizarClientes);
        btnFiltrar = (Button) findViewById(R.id.btnFiltrarCliente);
        etFiltrarTel = (EditText) findViewById(R.id.etFiltrarTel);

        btnCrear.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);
        btnFiltrar.setOnClickListener(this);

        mostrarDatos("SELECT * FROM " + BaseDeDatos.CLIENTES_TABLA);
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
        //Si es el botón crear iniciamos la actividad de crear cliente
        if (v.getId() == btnCrear.getId()) {
            startActivity(new Intent(this, CrearClienteActivity.class));

        }
        //Si es el botón actualizar actualizamos los datos del list view
        else if (v.getId() == btnActualizar.getId()) {
            mostrarDatos("SELECT * FROM " + BaseDeDatos.CLIENTES_TABLA);
            Toast.makeText(this, getString(R.string.lista_clientes_actualizada), Toast.LENGTH_SHORT).show();

        }
        //Si es el botón filtrar, filtramos los datos del listview por el campo teléfono con el teléfono introducido en el edit text (etFiltrarTel)
        else if (v.getId() == btnFiltrar.getId()) {
            if (etFiltrarTel.getText().toString().length() == 9 && !etFiltrarTel.getText().toString().isEmpty()) {

                mostrarDatos("SELECT * FROM " + BaseDeDatos.CLIENTES_TABLA + " WHERE " + BaseDeDatos.TELEFONO + " = " + etFiltrarTel.getText().toString());
                Toast.makeText(this, getString(R.string.filtrar_telefono, etFiltrarTel.getText().toString()), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.error_telefono), Toast.LENGTH_SHORT).show();

            }
        }
    }

    /**
     * Método para mostrar los datos obtenidos de una sentencia de la base de datos en el list view principal
     * @param sentencia (Sentencia de búsqueda de datos en la base de datos)
     */
    private void mostrarDatos(String sentencia) {
        Cursor c1 = db.rawQuery(sentencia, null);

        String[] nombresColumnas = new String[]{BaseDeDatos.CLIENTE_ID, BaseDeDatos.NOMBRE, BaseDeDatos.TELEFONO, BaseDeDatos.UBICACION};
        int[] referencias = new int[]{R.id.tvId, R.id.tvNombre, R.id.tvTel, R.id.tvUbicacion};

        CustomAdaptadorClientes customAdaptadorClientes = new CustomAdaptadorClientes(this, R.layout.list_clientes, c1, nombresColumnas, referencias, 0);
        lstClientes.setAdapter(customAdaptadorClientes);

    }
}
