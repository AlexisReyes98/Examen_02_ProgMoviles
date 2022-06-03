package com.example.examen_02_progmoviles;

import java.io.Serializable;

public class RegistroDeportivo implements Serializable {
    private String nombreEquipo;
    private String nombreCapitan;
    private String telefonoCapitan;
    private String categoria;
    private String uniformeColor;
    private String numEquipo;

    public RegistroDeportivo() {
    }

    public RegistroDeportivo(String nombreEquipo, String nombreCapitan, String telefonoCapitan, String categoria, String uniformeColor) {
        this.nombreEquipo = nombreEquipo;
        this.nombreCapitan = nombreCapitan;
        this.telefonoCapitan = telefonoCapitan;
        this.categoria = categoria;
        this.uniformeColor = uniformeColor;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreCapitan() {
        return nombreCapitan;
    }

    public void setNombreCapitan(String nombreCapitan) {
        this.nombreCapitan = nombreCapitan;
    }

    public String getTelefonoCapitan() {
        return telefonoCapitan;
    }

    public void setTelefonoCapitan(String telefonoCapitan) {
        this.telefonoCapitan = telefonoCapitan;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUniformeColor() {
        return uniformeColor;
    }

    public void setUniformeColor(String uniformeColor) {
        this.uniformeColor = uniformeColor;
    }

    public String getNumEquipo() {
        return numEquipo;
    }

    public void setNumEquipo(String numEquipo) {
        this.numEquipo = numEquipo;
    }

    @Override
    public String toString() {
        return  "Equipo Registrado No. " + numEquipo + "\n" +
                "Nombre del Equipo: " + nombreEquipo + "\n" +
                "Nombre del Capitán: " + nombreCapitan + "\n" +
                "Telefono del Capitán: " + telefonoCapitan + "\n" +
                "Categoría: " + categoria + "\n" +
                "Color del uniforme: " + uniformeColor + "\n";
    }
}
