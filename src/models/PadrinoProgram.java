package models;

public class PadrinoProgram {
/*
 * This class is created to solve the requirement:
*Listar los padrinos con los programas a los que aporta (DNI, Apellido y
*nombre, nombre de programa, monto y frecuencia del aporte).
 */

    private int dni;
    private String apellido;
    private String nombre;
    private String nombrePrograma;
    private int monto;
    private String frecuenciaAporte;

    public PadrinoProgram() {
    }

    public PadrinoProgram(int dni, String apellido, String nombre, String nombrePrograma, int monto, String frecuenciaAporte) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.nombrePrograma = nombrePrograma;
        this.monto = monto;
        this.frecuenciaAporte = frecuenciaAporte;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getFrecuenciaAporte() {
        return frecuenciaAporte;
    }

    public void setFrecuenciaAporte(String frecuenciaAporte) {
        this.frecuenciaAporte = frecuenciaAporte;
    }
}
