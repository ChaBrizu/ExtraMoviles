package com.chabrizu.rest.extrainventario.baseDatos;

public class Producto {
    private int id;
    private String id_producto;
    private String nombre;
    private float precio;
    private String modelo;
    private String descripcion;

    public Producto(String id_producto, String nombre, float precio, String modelo, String descripcion){
        this.id_producto= id_producto;
        this.nombre=nombre;
        this.precio=precio;
        this.modelo=modelo;
        this.descripcion=descripcion;
    }

    public int getId() {
        return id;
    }

    public String getId_producto() {
        return id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public String getModelo() {
        return modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
