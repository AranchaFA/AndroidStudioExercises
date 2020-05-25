package com.example.rvfragment1.Equipos;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Equipos {


    public static final List<Equipo> ITEMS = new ArrayList<Equipo>();


    private static void addItem(Equipo item) {
        ITEMS.add(item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Equipo {
        public final String nombre;
        public final int puntos;
        public final Drawable escudo;

        public Equipo(String nombre, int puntos, Drawable escudo) {
            this.nombre = nombre;
            this.puntos = puntos;
            this.escudo = escudo;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPuntos() {
            return puntos;
        }

        public Drawable getEscudo() {
            return escudo;
        }

    }
}
