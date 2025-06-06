package models;

import java.math.BigInteger;

public class Donante {
 private int dni;
 private String ocupacion;
 private long cuit;

    public Donante() {
    }

    public Donante(int dni, String ocupacion, long cuit) {
        this.dni = dni;
        this.ocupacion = ocupacion;
        this.cuit = cuit;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }
}
