package com.chabrizu.rest.extrainventario;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chabrizu.rest.extrainventario.baseDatos.AdaptadorProducto;
import com.chabrizu.rest.extrainventario.baseDatos.CamposBD;
import com.chabrizu.rest.extrainventario.baseDatos.ConSQLiteHelper;
import com.chabrizu.rest.extrainventario.baseDatos.Producto;

import java.util.ArrayList;

public class ViewProductos extends AppCompatActivity implements AdaptadorProducto.ClicListener{

    ArrayList<Producto> listProducto;
    RecyclerView recyclerViewProducto;
    ConSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_productos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.btnProduct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        conn= new ConSQLiteHelper(this, CamposBD.TABLA_PRODUCTO, null, 1);

        listProducto=new ArrayList<>();

        recyclerViewProducto=findViewById(R.id.recViewProducto);
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(this));

        consultarListaProd();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(ViewProductos.this, AddProducto.class);
            startActivity(intent);
            }
        });


    }

    private void consultarListaProd() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Producto producto = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + CamposBD.TABLA_PRODUCTO +";", null);

        while (cursor.moveToNext()){
            producto = new Producto(null,null,0,null,null);
            producto.setId(cursor.getInt(0));
            producto.setId_producto(cursor.getString(1));
            producto.setNombre(cursor.getString(2));
            producto.setPrecio(cursor.getFloat(3));
            producto.setModelo(cursor.getString(4));
            producto.setDescripcion(cursor.getString(5));

            listProducto.add(producto);
        }

        AdaptadorProducto adaptador = new AdaptadorProducto(listProducto,this);
        recyclerViewProducto.setAdapter(adaptador);
        db.close();
    }

    @Override
    public void onCardClick(int pos) {
        int id=listProducto.get(pos).getId();
        Intent edit= new Intent(this, EditProducto.class);
        edit.putExtra("Posicion", id);
        startActivity(edit);
    }
}
