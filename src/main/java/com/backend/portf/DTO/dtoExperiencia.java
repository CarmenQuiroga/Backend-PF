package com.backend.portf.DTO;

import javax.validation.constraints.NotBlank;

public class dtoExperiencia {

   @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    @NotBlank
    private String fechaE;
    @NotBlank
    private String tareaE;
   
    public String getFechaE() {
        return fechaE;
    }

    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }

    public String getTareaE() {
        return tareaE;
    }

    public void setTareaE(String tareaE) {
        this.tareaE = tareaE;
    }

    public dtoExperiencia() {
    }

    public dtoExperiencia(@NotBlank String nombreE, @NotBlank String descripcionE, @NotBlank String fechaE, @NotBlank String tareaE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.fechaE = fechaE;
        this.tareaE = tareaE;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }




}
