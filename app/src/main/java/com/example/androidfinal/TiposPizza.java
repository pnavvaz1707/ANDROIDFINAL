package com.example.androidfinal;

public enum TiposPizza {

    Carbonara(11), Prosciutto(10.5), Barbacoa(11.5), Peperonni(12.5),
    Champinones(12.5), Margarita(9.99);

    private final double PRECIO;

    TiposPizza(double precio) {
        this.PRECIO = precio;
    }

    public double getPrecio() {
        return PRECIO;
    }
}
