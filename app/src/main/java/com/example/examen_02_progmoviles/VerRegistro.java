package com.example.examen_02_progmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VerRegistro extends AppCompatActivity {

    private ListView lista;
    private RegistroDeportivo registro;
    private ArrayList<RegistroDeportivo> myList;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registro);
        compInterfaz();
        myList = (ArrayList<RegistroDeportivo>) getIntent().getSerializableExtra("Lista");
        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myList.isEmpty() == true) {
                    listaVacia();
                }
                else  {
                    // imprimeLista();
                    imprimeLista2();
                }
            }
        });
        regresaMain();
    }

    public void compInterfaz() {
        lista = (ListView) findViewById(R.id.lista);
    }
/*
    public void imprimeLista() {
        /*
        Para poder cargar los datos en el ListView se tiene que definir un objeto de tipo ArrayAdapter
        El cual debe coincidir con el tipo de dato de la lista original, que es de tipo RegistroDeportivo
        En los parentesis se coloca el contexto actual que es VerRegistro
        Seguido por el tipo de lista que se va a presentar, en este caso una lista simple
        y al final se pasa la lista original

        ArrayAdapter<RegistroDeportivo> adaptador = new ArrayAdapter<RegistroDeportivo>(VerRegistro.this, android.R.layout.simple_list_item_1, myList);
        // El adaptador es lo que permitira pasar el contenido al ListView
        lista.setAdapter(adaptador);
    }
*/
    public void imprimeLista2() {
        try {
            jsonArray = leeJson();
            ArrayList<RegistroDeportivo> myList2 = new ArrayList<RegistroDeportivo>();
            for (int i = 0; i < jsonArray.length() - 1; i++) {
                JSONObject jRegistro = jsonArray.getJSONObject(i);
                registro = new RegistroDeportivo();
                registro.setNumEquipo(jRegistro.get("Numero Equipo").toString());
                registro.setNombreEquipo(jRegistro.get("Nombre del Equipo").toString());
                registro.setNombreCapitan(jRegistro.get("Nombre del Capitán").toString());
                registro.setTelefonoCapitan(jRegistro.get("Telefono del Capitán").toString());
                registro.setCategoria(jRegistro.get("Categoría").toString());
                registro.setUniformeColor(jRegistro.get("Color del uniforme").toString());
                myList2.add(registro);
            }
            ArrayAdapter<RegistroDeportivo> adaptador = new ArrayAdapter<RegistroDeportivo>(VerRegistro.this, android.R.layout.simple_list_item_1, myList2);
            lista.setAdapter(adaptador);
        } catch (IOException e) {
            Toast.makeText(this, "Error al Leer el Archivo", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Toast.makeText(this, "Error en formato JSON", Toast.LENGTH_SHORT).show();
        }
    }

    public void listaVacia() {
        Toast.makeText(this, "La lista actual esta vacía...", Toast.LENGTH_SHORT).show();
    }

    // Para digamos practicar, implemente una clase que referencia a la interfaz View.OnClickListener
    // Como se vio en clase para manejar Eventos
    public void regresaMain() {
        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new MiClick());
    }

    class MiClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            intent.putExtra("Lista", myList);
            setResult(RESULT_OK, intent);
            finish();
            /*
            Lo importante en esta actividad es el código que está dentro de este onCLickListener
            Una vez creado el Intent, el cual tendrá el elemento seleccionado (nuestra lista)
            Una vez esté cargado, se llama al método setResult() el cual tiene la constante
            RESULT_OK, es decir, que el resultado que se envia es correcto. Si por el contrario
            se quisiera retroceder a la actividad anterior sin enviar nada se podría establecer
            RESULT_CANCELLED. Como segundo argumento recibe el Intent con los datos que se han
            cargado en el. Una vez hecho esto llamamos al método finish() el cual finalizará la
            actividad y volverá a la primera actividad.
             */
        }
    }

    private JSONArray leeJson() throws IOException, JSONException {
        File archivo = new File(ContextCompat.getExternalFilesDirs(this, null)[1],"registro.json");
        FileReader leeArchivo = new FileReader(archivo);
        BufferedReader leeBuffer = new BufferedReader(leeArchivo);
        StringBuilder strBuilder = new StringBuilder();
        String linea = leeBuffer.readLine();
        while (linea != null) {
            strBuilder.append(linea).append("\n");
            linea = leeBuffer.readLine();
        }
        leeBuffer.close();
        String strRegistro = strBuilder.toString();
        JSONArray jRegistro = new JSONArray(strRegistro);

        return jRegistro;
    }

}