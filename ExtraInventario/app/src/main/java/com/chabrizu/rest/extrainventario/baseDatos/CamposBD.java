package com.chabrizu.rest.extrainventario.baseDatos;

public class CamposBD {
    public static final String TABLA_PRODUCTO="Producto";
    public static final String ID="id";
    public static final String ID_PRODUCTO="id_producto";
    public static final String NOMBRE_PRODUCTO="nombre";
    public static final String PRECIO_PRODUCTO="precio";
    public static final String MODELO_PRODUCTO="modelo";
    public static final String DESCRIPCION_PRODUCTO="descripcion";

    public static final String CREAR_TABLA_PRODUCTO="CREATE TABLE "+ TABLA_PRODUCTO +"("
            + ID + " INTEGER PRIMARY KEY ASC, "
            + ID_PRODUCTO + " TEXT NOT NULL, "
            + NOMBRE_PRODUCTO + " TEXT NOT NULL, "
            + PRECIO_PRODUCTO + " REAL NOT NULL, "
            + MODELO_PRODUCTO + " TEXT, "
            + DESCRIPCION_PRODUCTO + " TEXT);";

}
