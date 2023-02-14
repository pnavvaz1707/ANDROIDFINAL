package com.example.androidfinal;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CustomAdaptadorClientes extends SimpleCursorAdapter implements View.OnClickListener {
    private Context contexto;
    private ImageButton btnVerPedidos;
    private TextView tvId;
    private TextView tvNombre;
    private TextView tvTel;

    public CustomAdaptadorClientes(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.contexto = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        tvId = (TextView) view.findViewById(R.id.tvId);
        tvNombre = (TextView) view.findViewById(R.id.tvNombre);
        tvTel = (TextView) view.findViewById(R.id.tvTel);

        btnVerPedidos = (ImageButton) view.findViewById(R.id.btnVerPedidos);
        btnVerPedidos.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Dialog dialog = new Dialog(contexto);
        dialog.setContentView(R.layout.pedidos_cliente);
        dialog.setTitle("Pedidos del cliente " + tvNombre.getText().toString());

        ListView lstPedidos = (ListView) dialog.findViewById(R.id.lstPedidosCliente);

        Cursor pedidosCliente = ClientesActivity.db.rawQuery("SELECT * FROM " + BaseDeDatos.PEDIDOS_TABLA + " WHERE " + BaseDeDatos.PEDIDOS_CLIENTE_ID + " = " + tvId.getText().toString(), null);

        String[] nombresColumnas = new String[]{BaseDeDatos.PEDIDO_ID, BaseDeDatos.PIZZA, BaseDeDatos.PIZZA, BaseDeDatos.PRECIO};
        int[] referencias = new int[]{R.id.tvIdPedidoCliente, R.id.tvNombrePizza, R.id.tvCantidadPizza, R.id.tvPrecioPizza};

        CustomAdaptadorPedidos adaptador = new CustomAdaptadorPedidos(contexto, R.layout.list_pedidos, pedidosCliente, nombresColumnas, referencias, 0);
        lstPedidos.setAdapter(adaptador);
        dialog.show();

    }
}
