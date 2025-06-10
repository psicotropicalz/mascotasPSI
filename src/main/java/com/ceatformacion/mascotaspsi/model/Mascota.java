package com.ceatformacion.mascotaspsi.model;

import jakarta.persistence.*;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private double peso;

    private String dni;

    // Getters y setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
}
