package models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class Padrino {

    private int dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String celular;
    private String telefonoFijo;
    private String email;
    private String facebook;
    private int codigoPostal;
    private Date fechaNacimiento;
    private int edad;


    public Padrino() {}  // Constructor vacío

    public Padrino(int dni, String nombre, String apellido, String direccion, String celular, String telefonoFijo, String email, String facebook, int codigoPostal, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.celular = celular;
        this.telefonoFijo = telefonoFijo;
        this.email = email;
        this.facebook = facebook;
        this.codigoPostal = codigoPostal;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return calcularEdad();
    }

    private int calcularEdad(){
        if (fechaNacimiento == null) return 0;
        LocalDate nacimiento = fechaNacimiento.toLocalDate();
        LocalDate hoy = LocalDate.now();
        return Period.between(nacimiento, hoy).getYears();
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    @Override
    public String toString() {
        return "Padrino{dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "}";
    }
}
