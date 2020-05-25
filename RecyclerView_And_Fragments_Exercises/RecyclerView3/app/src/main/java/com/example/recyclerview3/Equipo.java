package com.example.recyclerview3;

import android.graphics.drawable.Drawable;

public class Equipo {

    public String nombre;
    public Drawable escudo;
    public int puntos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Drawable getEscudo() {
        return escudo;
    }

    public void setEscudo(Drawable escudo) {
        this.escudo = escudo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Equipo(String nombre, Drawable escudo, int puntos) {
        this.nombre = nombre;
        this.escudo = escudo;
        this.puntos = puntos;
    }
}
