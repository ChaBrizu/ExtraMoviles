package com.chabrizu.rest.extrainventario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chabrizu.rest.extrainventario.baseDatos.CamposBD;
import com.chabrizu.rest.extrainventario.baseDatos.ConSQLiteHelper;

public class AddProducto extends AppCompatActivity {

    EditText txId, txNombre, txPrecio, txModelo, txDescripcion;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ctx_menu_producto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idBorras = item.getItemId();

        if (idBorras == R.id.btnElBorras) {
            Toast.makeText(getApplicationContext(),"CLICLCLCLALALD",Toast.LENGTH_LONG).show();
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_producto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_add_producto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txId=(EditText) findViewById(R.id.txtId);
        txNombre=(EditText) findViewById(R.id.txtNombre);
        txPrecio=(EditText) findViewById(R.id.txtPrecio);
        txModelo=(EditText) findViewById(R.id.txtMod);
        txDescripcion=(EditText) findViewById(R.id.txtDescripcion);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altaProducto();
                Intent intent=new Intent(AddProducto.this, ViewProductos.class);
                startActivity(intent);
                finish();
            }

            private void altaProducto(){
                ConSQLiteHelper conn = new ConSQLiteHelper(AddProducto.this, CamposBD.TABLA_PRODUCTO, null, 1);

                SQLiteDatabase db=conn.getWritableDatabase();

                ContentValues val=new ContentValues();
                val.put(CamposBD.ID_PRODUCTO, txId.getText().toString());
                val.put(CamposBD.NOMBRE_PRODUCTO, txNombre.getText().toString());
                val.put(CamposBD.PRECIO_PRODUCTO, txPrecio.getText().toString());
                val.put(CamposBD.MODELO_PRODUCTO, txModelo.getText().toString());
                val.put(CamposBD.DESCRIPCION_PRODUCTO, txDescripcion.getText().toString());

                Long idResult=db.insert(CamposBD.TABLA_PRODUCTO, CamposBD.ID, val);

                Toast.makeText(getApplicationContext(), "Id Registro: "+idResult,Toast.LENGTH_SHORT).show();
                db.close();
            }
        });
    }

}
