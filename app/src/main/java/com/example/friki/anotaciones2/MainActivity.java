package com.example.friki.anotaciones2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText categoriaET;
    private EditText anotacionET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoriaET = (EditText) findViewById(R.id.categoriaET);
        anotacionET = (EditText) findViewById(R.id.anotacionET);


    }

    public void guardarNotas(View view) {
        try {
            //abrimos la coneccion con la bd
            dbHelper db = new dbHelper(getApplicationContext(), null, null, 1);
            db.abrir();


            // aqui optenemos la fecha actual con Calendar


            Calendar c = Calendar.getInstance();
            int año = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.YEAR);
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            String s = formatter.format(c.getTime());

            String editStr = anotacionET.getText().toString().trim();
            String categ = categoriaET.getText().toString();

            if (categ.isEmpty()) {
                categ = "Apuntes";
                db.insertarregistro(categ, editStr, s);
                Toast.makeText(getApplicationContext(), "Nota Registrada", Toast.LENGTH_LONG).show();
            } else {
                db.insertarregistro(String.valueOf(categoriaET.getText()), editStr, s);
                Toast.makeText(getApplicationContext(), "Nota Registrada", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Campos vacios", Toast.LENGTH_LONG).show();

        }
    }


    public void verNotasGuardas(View view) {

        Intent vernotas = new Intent(this, verDatosGuardados.class);
        startActivity(vernotas);
    }


}
