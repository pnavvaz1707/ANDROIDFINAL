package com.example.androidfinal;

public class Pizza {
    private TiposPizza tipo;
    private int cantidad;

    public Pizza(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
