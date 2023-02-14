package com.example.androidfinal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CustomAdaptadorPedidos extends SimpleCursorAdapter {
    private TextView tvId;
    private TextView tvNombre;
    private TextView tvCantidad;
    private TextView tvPrecio;

    public CustomAdaptadorPedidos(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        tvId = (TextView) view.findViewById(R.id.tvIdPedidoCliente);
        tvNombre = (TextView) view.findViewById(R.id.tvNombrePizza);
        tvCantidad = (TextView) view.findViewById(R.id.tvCantidadPizza);
        tvPrecio = (TextView) view.findViewById(R.id.tvPrecioPizza);

        return view;
    }
}
