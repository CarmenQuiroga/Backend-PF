package com.backend.portf.DTO;

import javax.validation.constraints.NotBlank;

public class dtoSkill {
    @NotBlank
    private String nombreSkill;
    @NotBlank
    private int porcentaje;
   
    
    public dtoSkill(){

    }

    public dtoSkill(String nombreSkill, int porcentaje){
        this.nombreSkill = nombreSkill;
        this.porcentaje = porcentaje;
        
    }

    
    public String getNombreSkill() {
        return nombreSkill;
    }

    public void setNombreSkill(String nombreSkill) {
        this.nombreSkill = nombreSkill;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

}
