package com.example.androidfinal;

import static com.example.androidfinal.InicioActivity.db;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdaptadorClientes extends SimpleCursorAdapter implements View.OnClickListener {
    private final Context CONTEXTO;
    private ImageButton btnVerPedidos;
    private ImageButton btnEliminarCliente;

    public CustomAdaptadorClientes(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.CONTEXTO = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        btnVerPedidos = (ImageButton) view.findViewById(R.id.btnVerPedidos);
        btnEliminarCliente = (ImageButton) view.findViewById(R.id.btnEliminarCliente);

        btnVerPedidos.setOnClickListener(this);
        btnEliminarCliente.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        LinearLayout layout = (LinearLayout) view.getParent();

        TextView tvId = (TextView) layout.findViewById(R.id.tvId);
        TextView tvNombre = (TextView) layout.findViewById(R.id.tvNombre);

        if (view.getId() == btnEliminarCliente.getId()) {
            if (db.delete(BaseDeDatos.CLIENTES_TABLA, BaseDeDatos.CLIENTE_ID + " = '" + tvId.getText().toString() + "'", null) > 0) {
                int numPedidosBorrados = db.delete(BaseDeDatos.PEDIDOS_TABLA, BaseDeDatos.PEDIDOS_CLIENTE_ID + " = '" + tvId.getText().toString() + "'", null);
                Toast.makeText(CONTEXTO, CONTEXTO.getString(R.string.cliente_eliminado, tvNombre.getText().toString(),numPedidosBorrados), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CONTEXTO, CONTEXTO.getString(R.string.cliente_no_eliminado, tvNombre.getText().toString()), Toast.LENGTH_SHORT).show();
            }

        } else if (view.getId() == btnVerPedidos.getId()) {
            Dialog dialog = new Dialog(CONTEXTO);
            dialog.setContentView(R.layout.pedidos_cliente);

            TextView tvTitulo = (TextView) dialog.findViewById(R.id.tvTituloPedidosCliente);
            tvTitulo.setText(CONTEXTO.getString(R.string.pedidos_cliente, tvNombre.getText().toString()));

            ListView lstPedidos = (ListView) dialog.findViewById(R.id.lstPedidosCliente);

            Cursor pedidosCliente = db.rawQuery("SELECT * FROM " + BaseDeDatos.PEDIDOS_TABLA + " WHERE " + BaseDeDatos.PEDIDOS_CLIENTE_ID + " = " + tvId.getText().toString(), null);

            String[] nombresColumnas = new String[]{BaseDeDatos.PEDIDO_ID, BaseDeDatos.PIZZA, BaseDeDatos.PRECIO, BaseDeDatos.CANTIDAD};
            int[] referencias = new int[]{R.id.tvIdPedido, R.id.tvNombrePizza, R.id.tvPrecioPizza, R.id.tvCantidadPizza};

            CustomAdaptadorPedidos adaptador = new CustomAdaptadorPedidos(CONTEXTO, R.layout.list_pedidos, pedidosCliente, nombresColumnas, referencias, 0);
            lstPedidos.setAdapter(adaptador);

            Cursor precioTotalCliente = db.rawQuery("SELECT SUM(" + BaseDeDatos.PRECIO + " * " + BaseDeDatos.CANTIDAD + ") FROM " + BaseDeDatos.PEDIDOS_TABLA + " WHERE " + BaseDeDatos.PEDIDOS_CLIENTE_ID + " = " + Integer.parseInt(tvId.getText().toString()), null);
            precioTotalCliente.moveToFirst();

            TextView tvPrecioTotal = (TextView) dialog.findViewById(R.id.tvPrecioTotalCliente);
            tvPrecioTotal.setText(CONTEXTO.getString(R.string.total_precio, tvNombre.getText().toString(), String.valueOf(precioTotalCliente.getDouble(0))));

            dialog.show();
        }

    }
}
