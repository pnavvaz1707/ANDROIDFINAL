package com.example.androidfinal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CrearClienteActivity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private EditText etNombre;
    private EditText etTelefono;
    private EditText etUbicacion;
    private Button btnCancelar;
    private Button btnCrear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cliente_window);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        db = ClientesActivity.db;

        etNombre = (EditText) findViewById(R.id.etNombre);
        etTelefono = (EditText) findViewById(R.id.etTel);
        etUbicacion = (EditText) findViewById(R.id.etUbicacion);
        btnCrear = (Button) findViewById(R.id.btnCrearClienteOk);

        btnCrear.setOnClickListener(this);
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
        insercion.put(BaseDeDatos.NOMBRE, etNombre.getText().toString());
        insercion.put(BaseDeDatos.TELEFONO, etTelefono.getText().toString());
        insercion.put(BaseDeDatos.UBICACION, etUbicacion.getText().toString());
        db.insert(BaseDeDatos.CLIENTES_TABLA, null, insercion);
    }
}
