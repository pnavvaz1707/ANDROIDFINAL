package com.example.androidfinal;

import static com.example.androidfinal.InicioActivity.db;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdaptadorPedidos extends SimpleCursorAdapter implements View.OnClickListener {
    private ImageButton btnEliminarPedido;
    private CheckBox checkEntregada;
    private final Context CONTEXTO;

    public CustomAdaptadorPedidos(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.CONTEXTO = context;
    }

    /**
     * Método que se ejecuta cada vez que se recibe un dato en el list view
     * @param position (Posición del dato)
     * @param convertView (Vista del dato recibido)
     * @param parent (Vista padre del list view)
     * @return Devuelve la vista que guarda el dato recibido
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        btnEliminarPedido = (ImageButton) view.findViewById(R.id.btnEliminarPedido);
        checkEntregada = (CheckBox) view.findViewById(R.id.chckEntregada);

        Cursor c = db.rawQuery("SELECT " + BaseDeDatos.ENTREGADA + " FROM " + BaseDeDatos.PEDIDOS_TABLA + " WHERE " + BaseDeDatos.PEDIDO_ID + " = " + Integer.parseInt(((TextView) view.findViewById(R.id.tvIdPedido)).getText().toString()), null);
        c.moveToFirst();
        int entregada = c.getInt(0);

        if (entregada == 0) {
            checkEntregada.setChecked(false);

        } else if (entregada == 1) {
            checkEntregada.setChecked(true);
        }

        checkEntregada.setOnClickListener(this);
        btnEliminarPedido.setOnClickListener(this);
        return view;
    }

    /**
     * Método que se ejecuta al hacer click en un botón
     * @param view (Botón pulsado)
     */
    @Override
    public void onClick(View view) {
        LinearLayout layout = (LinearLayout) view.getParent();

        TextView tvIdPedido = (TextView) layout.findViewById(R.id.tvIdPedido);
        TextView tvNombrePizza = (TextView) layout.findViewById(R.id.tvNombrePizza);

        //Si es el botón de eliminar pedidos, eliminamos el pedido de la base de datos
        if (view.getId() == btnEliminarPedido.getId()) {
            if (db.delete(BaseDeDatos.PEDIDOS_TABLA, BaseDeDatos.PEDIDO_ID + " = '" + tvIdPedido.getText().toString() + "'", null) > 0) {
                Toast.makeText(CONTEXTO, CONTEXTO.getString(R.string.pedido_eliminado, tvNombrePizza.getText().toString()), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CONTEXTO, CONTEXTO.getString(R.string.pedido_no_eliminado, tvNombrePizza.getText().toString()), Toast.LENGTH_SHORT).show();
            }
        }//Si es el checkbox de pedido entregado, modificamos el campo entregada de la base de datos a 1 (si está entregada) o a 0 (si no lo está)
        else if (view.getId() == checkEntregada.getId()) {
            checkEntregada = (CheckBox) layout.findViewById(R.id.chckEntregada);
            if (checkEntregada.isChecked()) {
                db.execSQL("UPDATE " + BaseDeDatos.PEDIDOS_TABLA + " SET " + BaseDeDatos.ENTREGADA + " = 1 WHERE " + BaseDeDatos.PEDIDO_ID + " = " + Integer.parseInt(tvIdPedido.getText().toString()));
            } else {
                db.execSQL("UPDATE " + BaseDeDatos.PEDIDOS_TABLA + " SET " + BaseDeDatos.ENTREGADA + " = 0 WHERE " + BaseDeDatos.PEDIDO_ID + " = " + Integer.parseInt(tvIdPedido.getText().toString()));
            }
        }
    }
}
