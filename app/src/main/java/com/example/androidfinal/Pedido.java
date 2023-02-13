package com.example.androidfinal;

public class Pedido {
    private int id;
    private TiposPizza tipo;
    private int cantidad;
    private Cliente cliente;

    public Pedido(int id, TiposPizza tipo, int cantidad, Cliente cliente) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TiposPizza getTipo() {
        return tipo;
    }

    public void setTipo(TiposPizza tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
