package com.example.friki.anotaciones2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class verDatosGuardados extends AppCompatActivity {
    private EditText ingresarDatosET;
    private ListView listaVw;
    ArrayList<String> lista;
    ArrayAdapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos_guardados);
        ingresarDatosET = (EditText) findViewById(R.id.IngresarDatosET);
        listaVw = (ListView) findViewById(R.id.datosListView);

        dbHelper helper = new dbHelper(getApplicationContext(), null, null, 1);
        lista = helper.llernarLista();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        listaVw.setAdapter(adaptador);


    }

    public void buscarPorFecha(View view) {

        dbHelper helper = new dbHelper(getApplicationContext(), null, null, 1);
        String fecha = ingresarDatosET.getText().toString();
        if (!fecha.isEmpty()) {
            lista = helper.buscarPorfecha(fecha);
            adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
            listaVw.setAdapter(adaptador);

        } else {
            Toast.makeText(this, "debes introducir el Fecha que deseas buscar", Toast.LENGTH_SHORT).show();
        }

    }

    public void buscarPorCategoria(View view) {

        dbHelper helper = new dbHelper(getApplicationContext(), null, null, 1);
        String categoria = ingresarDatosET.getText().toString();
        if (!categoria.isEmpty()) {
            lista = helper.buscarPorCategoria(categoria);
            adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
            listaVw.setAdapter(adaptador);

        } else {
            Toast.makeText(this, "debes introducir el Categoria que deseas buscar", Toast.LENGTH_SHORT).show();
        }

    }


}
