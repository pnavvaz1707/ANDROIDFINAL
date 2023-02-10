package com.example.androidfinal;

import java.util.ArrayList;

public class Pedido {
    private int id;
    private ArrayList<Pizza> pizzas;
    private Cliente cliente;

    public Pedido(int id, ArrayList<Pizza> pizzas, Cliente cliente) {
        this.id = id;
        this.pizzas = pizzas;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Pizza> getProductos() {
        return pizzas;
    }

    public void setProductos(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
