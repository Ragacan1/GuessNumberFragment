package com.example.guessnumberfragment.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Random;

/**
 * Clase que gestiona datos del jugador.
 * Se establecen campos necesarios para el desarrollo de la aplicación como nombre, intentos, numero a adivinar y estado actual de la partida
 */
public class Jugador implements Serializable, Parcelable {
    public final static String KEY = "jugador";

    String nombre;
    int nIntentos;
    int nIntentosActual;
    int numAdivinar;
    String partida;


    public Jugador() {

    }

    public Jugador(String nombre, int nIntentos) {
        this.nombre = nombre;
        this.nIntentos = nIntentos;
        this.nIntentosActual = 0;
        this.numAdivinar = 1 + new Random().nextInt(100);
        this.partida = "En juego";

    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getnIntentos() {
        return nIntentos;
    }

    public void setnIntentos(int nIntentos) {
        this.nIntentos = nIntentos;
    }

    public int getnIntentosActual() {
        return nIntentosActual;
    }

    public void setnIntentosActual(int nIntentosActual) {
        this.nIntentosActual = nIntentosActual;
    }

    public int getNumAdivinar() {
        return numAdivinar;
    }

    public void setNumAdivinar(int numAdivinar) {
        this.numAdivinar = numAdivinar;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeInt(nIntentos);
        parcel.writeInt(nIntentosActual);
        parcel.writeInt(numAdivinar);
        parcel.writeString(partida);
    }

    protected Jugador(Parcel in) {
        nombre = in.readString();
        nIntentos = in.readInt();
        nIntentosActual = in.readInt();
        numAdivinar = in.readInt();
        partida = in.readString();
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };
}
