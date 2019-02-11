package com.chabrizu.rest.extrainventario;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chabrizu.rest.extrainventario.baseDatos.ConSQLiteHelper;

import java.net.ConnectException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnProductos, btnVentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProductos=(Button) findViewById(R.id.btnProductos);
        btnProductos.setOnClickListener(this);
        btnVentas=(Button)findViewById(R.id.btnVentas);
        btnVentas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnProductos:
                Intent intent = new Intent(this, ViewProductos.class);
                startActivity(intent);
                break;

            case R.id.btnVentas:
                break;
        }
    }
}
