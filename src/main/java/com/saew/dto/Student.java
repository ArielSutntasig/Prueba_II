package com.saew.dto;

public class Student {

    private int id; // Clave primaria
    private String nombre; // Cadena
    private String correoElectronico; // Cadena
    private float GPA; // Float

    public Student() {
    }

    public Student(int id, String nombre, String correoElectronico, float GPA) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.GPA = GPA;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public float getGPA() {
        return GPA;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }
}
