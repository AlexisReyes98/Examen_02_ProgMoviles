package com.example.examen_02_progmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int requestCode = 1;
    private Button btn1, btn2, btn3;
    private ArrayList<RegistroDeportivo> myList;   // Lista para guardar los objetos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = new ArrayList<RegistroDeportivo>();
        escuchaBotones();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:   // Nuevo reistro
                Intent intent = new Intent(v.getContext(), NuevoRegistro.class);
                intent.putExtra("Lista", myList);
                startActivityForResult(intent, requestCode);
                break;
            case R.id.btn2:   // Carga registro
                try {
                    guardaJson();
                    Toast.makeText(this, "Cargando Archivo JSON", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // e.printStackTrace();
                    Toast.makeText(this, "Error al Cargar el Archivo", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn3:   // Ver registro
                Intent intent2 = new Intent(v.getContext(), VerRegistro.class);
                intent2.putExtra("Lista", myList);
                startActivityForResult(intent2, requestCode);
                break;
            default:
                break;
        }
    }

    public void escuchaBotones() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    // Este método es el encargado de gestionar el Intent que se recibe de otra actividad
    // Este método tiene los parámetros requestCode, resultCode y data.
    // Los 2 primeros parámetros se usan para comprobar si el requestCode es igual
    // que el que se definio en la actividad y si el resultCode tiene el mismo valor
    // que la constante RESULT_OK, entonces se extraen los datos que trae consigo el Intent
    // en este caso nuestra Lista
    @Override
    protected void onActivityResult(int requestCode2, int resultCode, Intent data) {
        super.onActivityResult(requestCode2, resultCode, data);
        if ((requestCode == requestCode2) && (resultCode == RESULT_OK)) {
            myList = (ArrayList<RegistroDeportivo>) data.getSerializableExtra("Lista");
        }
    }

    // Método para escribir en el archivo JSON
    public void guardaJson() throws IOException {
        File archivo = new File(ContextCompat.getExternalFilesDirs(this, null)[1],"registro.json");
        FileWriter escibeArchivo = new FileWriter(archivo);
        BufferedWriter escribeBuffer = new BufferedWriter(escibeArchivo);
        escribeBuffer.write("[");
        int cont = 1;
        for (RegistroDeportivo r:myList) {
            JSONObject jRegistro = new JSONObject();
            try {
                jRegistro.put("Numero Equipo", cont);
                jRegistro.put("Nombre del Equipo", r.getNombreEquipo().toString());
                jRegistro.put("Nombre del Capitán", r.getNombreCapitan().toString());
                jRegistro.put("Telefono del Capitán", r.getTelefonoCapitan().toString());
                jRegistro.put("Categoría", r.getCategoria().toString());
                jRegistro.put("Color del uniforme", r.getUniformeColor().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String strRegistro = jRegistro.toString();
            escribeBuffer.write(strRegistro);
            escribeBuffer.write(",");
            cont++;
        }
        escribeBuffer.write("]");
        escribeBuffer.close();
    }
}