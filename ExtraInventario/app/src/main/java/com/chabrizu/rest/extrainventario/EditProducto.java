package com.chabrizu.rest.extrainventario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chabrizu.rest.extrainventario.baseDatos.CamposBD;
import com.chabrizu.rest.extrainventario.baseDatos.ConSQLiteHelper;

public class EditProducto extends AppCompatActivity {

    EditText txId, txNombre, txPrecio, txModelo, txDescripcion;
    ConSQLiteHelper conn = new ConSQLiteHelper(EditProducto.this, CamposBD.TABLA_PRODUCTO, null, 1);

    private int recibirDatos(){
        Bundle obtenerId = getIntent().getExtras();
        int idProdBndl = obtenerId.getInt("Posicion");
        return idProdBndl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_producto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_add_producto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txId=(EditText) findViewById(R.id.txtId);
        txNombre=(EditText) findViewById(R.id.txtNombre);
        txPrecio=(EditText) findViewById(R.id.txtPrecio);
        txModelo=(EditText) findViewById(R.id.txtMod);
        txDescripcion=(EditText) findViewById(R.id.txtDescripcion);

        SQLiteDatabase db = conn.getReadableDatabase();

        int idProdBndl=recibirDatos();
        String[] parametros={Integer.toString(idProdBndl)};

        try{
            Cursor cursor = db.rawQuery("SELECT * FROM " + CamposBD.TABLA_PRODUCTO +
                    " WHERE " + CamposBD.ID + "=?;",parametros);

            cursor.moveToFirst();

            txId.setText(cursor.getString(1));
            txNombre.setText(cursor.getString(2));
            txPrecio.setText(Float.toString(cursor.getFloat(3)));
            txModelo.setText(cursor.getString(4));
            txDescripcion.setText(cursor.getString(5));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"EError",Toast.LENGTH_LONG).show();
            this.finish();
        }



        //Long idResult=db.insert(CamposBD.TABLA_PRODUCTO, CamposBD.ID, val);

        //Toast.makeText(getApplicationContext(), "Id Registro: "+idResult,Toast.LENGTH_SHORT).show();


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProducto();
                Intent intent=new Intent(EditProducto.this, ViewProductos.class);
                startActivity(intent);
                finish();
            }

            private void updateProducto(){
                try{
                    SQLiteDatabase db=conn.getWritableDatabase();

                    int idProdBndl=recibirDatos();
                    String[] parametros={Integer.toString(idProdBndl)};

                    ContentValues values=new ContentValues();

                    values.put(CamposBD.ID_PRODUCTO,txId.getText().toString());
                    values.put(CamposBD.NOMBRE_PRODUCTO,txNombre.getText().toString());
                    values.put(CamposBD.PRECIO_PRODUCTO,txPrecio.getText().toString());
                    values.put(CamposBD.MODELO_PRODUCTO,txModelo.getText().toString());
                    values.put(CamposBD.DESCRIPCION_PRODUCTO,txDescripcion.getText().toString());

                    db.update(CamposBD.TABLA_PRODUCTO,values,CamposBD.ID+"=?",parametros);
                    Toast.makeText(getApplicationContext(),"Producto actualizado correctamente",Toast.LENGTH_LONG).show();
                    db.close();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }

}
