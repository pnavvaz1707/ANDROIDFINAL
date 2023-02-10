package com.example.androidfinal;

import java.util.ArrayList;

public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String ubicacion;
    private ArrayList<Pedido> pedidos;

    public Cliente(int id, String nombre, String telefono, String ubicacion, ArrayList<Pedido> pedidos) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.pedidos = pedidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
