package com.ceatformacion.mascotaspsi.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate fechaVisita;
    private String veterinario;
    private String motivoConsulta;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;

    // Si necesito conectarlo con Mascota
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    // Getters y Setters


    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public LocalDate getFechaVisita() { return fechaVisita;}

    public void setFechaVisita(LocalDate fechaVisita) { this.fechaVisita = fechaVisita;}

    public String getVeterinario() { return veterinario;}

    public void setVeterinario(String veterinario) { this.veterinario = veterinario;}

    public String getMotivoConsulta() { return motivoConsulta;}

    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta;}

    public String getDiagnostico() { return diagnostico;}

    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico;}

    public String getTratamiento() { return tratamiento;}

    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento;}

    public String getObservaciones() { return observaciones;}

    public void setObservaciones(String observaciones) { this.observaciones = observaciones;}

    public Mascota getMascota() { return mascota;}

    public void setMascota(Mascota mascota) { this.mascota = mascota;}
}
