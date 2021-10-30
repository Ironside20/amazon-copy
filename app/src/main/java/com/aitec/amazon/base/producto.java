package com.aitec.amazon.base;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class producto extends RealmObject {
    //defición de atributos
    @PrimaryKey
    private int codigo;
    @Required
    private String descripcion;
    private double precioCompra;
    private double pvp;
    private int cantidad;

    //constructor
    public producto() {
    }

    public producto(int codigo, String descripcion, double precioCompra, int cantidad) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.pvp = precioCompra*1.2;
    }

    //métodos de acceso
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
