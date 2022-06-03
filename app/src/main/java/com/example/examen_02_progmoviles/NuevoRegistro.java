package com.example.examen_02_progmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class NuevoRegistro extends AppCompatActivity implements View.OnClickListener {

    private RegistroDeportivo registro; // Cree un objeto de la clase RegistroDeportivo
    private EditText nombreEquipo, nombreCapitan, telefonoCapitan, uniformeColor;
    private RadioButton masculino, femenino;
    private Button btn4, btn5;
    private ArrayList<RegistroDeportivo> myList;
    public static String[] colores = {"Negro", "Rosa", "Azul claro", "Azul fuerte", "Morado", "Blanco"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_registro);
        escuchaBotones();
        obtieneRegistroInterfaz();
        myList = (ArrayList<RegistroDeportivo>) getIntent().getSerializableExtra("Lista");
        AutoCompleteTextView opColor = (AutoCompleteTextView) findViewById(R.id.uniformeColor);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(NuevoRegistro.this, android.R.layout.simple_dropdown_item_1line, colores);
        opColor.setAdapter(adaptador);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn4:   // Agrego el registro a la lista
                if (validaRegistro()) {
                    cargaRegistro();
                }
                break;
            case R.id.btn5:   // Regreso a menu
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("Lista", myList);   // Una vez cargado el intent
                setResult(RESULT_OK, intent);   // Se llama al método setResult()
                // El cual tiene la constante RESULT_OK, es decir, que el resultado que
                // se envía es correcto
                finish(); // Por último se llama al método finish() el cual termina
                // la actividad y volverá a la primera actividad
                // Al llamar a finish() en una actividad se ejecuta el metodo onDestroy()
                break;
            default:
                break;
        }
    }

    public void escuchaBotones() {
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    public void obtieneRegistroInterfaz() {
        nombreEquipo = (EditText) findViewById(R.id.nombreEquipo);
        nombreCapitan = (EditText) findViewById(R.id.nombreCapitan);
        telefonoCapitan = (EditText) findViewById(R.id.telefonoCapitan);
        masculino = (RadioButton) findViewById(R.id.masculino);
        femenino = (RadioButton) findViewById(R.id.femenino);
        uniformeColor = (EditText) findViewById(R.id.uniformeColor);
    }

    public void cargaRegistro() {
        registro = new RegistroDeportivo();
        registro.setNombreEquipo(nombreEquipo.getText().toString());
        registro.setNombreCapitan(nombreCapitan.getText().toString());
        registro.setTelefonoCapitan(telefonoCapitan.getText().toString());
        if (masculino.isChecked()) {
            registro.setCategoria("Masculino");
        }
        else if (femenino.isChecked()) {
            registro.setCategoria("Femenino");
        }
        registro.setUniformeColor(uniformeColor.getText().toString());
        myList.add(registro);
        Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
        nombreEquipo.setText("");
        nombreCapitan.setText("");
        telefonoCapitan.setText("");
        masculino.setChecked(false);
        femenino.setChecked(false);
        uniformeColor.setText("");
    }

    public boolean validaEquipo(String equipo) {
        Pattern expReg = Pattern.compile("^[A-Za-z ]+$");
        if (!expReg.matcher(equipo).matches()) {
            this.nombreEquipo.setError("Nombre invalido");
            return false;
        }
        else {
            this.nombreEquipo.setError(null);
        }
        return true;
    }

    public boolean validaCapitan(String capitan) {
        Pattern expReg = Pattern.compile("^[A-Za-z ]+$");
        if (!expReg.matcher(capitan).matches()) {
            this.nombreCapitan.setError("Nombre invalido");
            return false;
        }
        else {
            this.nombreCapitan.setError(null);
        }
        return true;
    }

    public boolean validaColor(String color) {
        Pattern expReg = Pattern.compile("^[A-Za-z ]+$");
        if (!expReg.matcher(color).matches()) {
            this.uniformeColor.setError("Color invalido");
            return false;
        }
        else {
            this.uniformeColor.setError(null);
        }
        return true;
    }

    public boolean validaRegistro() {
        String equipo = nombreEquipo.getText().toString();
        String capitan = nombreCapitan.getText().toString();
        String color = uniformeColor.getText().toString();
        if (validaEquipo(equipo) && validaCapitan(capitan) && validaColor(color)) {
            return true;
        }
        else {
            return false;
        }
    }

}