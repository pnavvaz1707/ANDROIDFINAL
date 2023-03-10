package com.example.androidfinal;

import static com.example.androidfinal.InicioActivity.db;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CrearClienteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNombre;
    private EditText etTelefono;
    private EditText etUbicacion;
    private Button btnCrear;

    /**
     * Método que se ejecuta al iniciar la activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cliente_window);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etTelefono = (EditText) findViewById(R.id.etTel);
        etUbicacion = (EditText) findViewById(R.id.etUbicacion);
        btnCrear = (Button) findViewById(R.id.btnCrearClienteOk);

        btnCrear.setOnClickListener(this);
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
        //Si es el botón crear, comprobamos que los datos estén bien introducidos y creamos el cliente en la base de datos
        if (v.getId() == btnCrear.getId()) {
            if (etNombre.getText().toString().trim().isEmpty() || etTelefono.getText().toString().trim().isEmpty() || etUbicacion.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.error_campos_vacio), Toast.LENGTH_SHORT).show();
            } else {
                if (etTelefono.getText().toString().length() != 9) {
                    Toast.makeText(this, getString(R.string.error_telefono), Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues insercion = new ContentValues();

                    insercion.put(BaseDeDatos.NOMBRE, etNombre.getText().toString());
                    insercion.put(BaseDeDatos.TELEFONO, etTelefono.getText().toString());
                    insercion.put(BaseDeDatos.UBICACION, etUbicacion.getText().toString());

                    db.insert(BaseDeDatos.CLIENTES_TABLA, null, insercion);

                    Toast.makeText(this, getString(R.string.cliente_creado, etNombre.getText().toString()), Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    etTelefono.setText("");
                    etUbicacion.setText("");
                }
            }
        }
    }
}
