package com.backend.portf.DTO;



import javax.validation.constraints.NotBlank;



public class dtoEducation {
@NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    @NotBlank
    private  String fechaE;

    public String getFechaE(){
        return fechaE;
    }
    public void setFechaE(String fechaE){
        this.fechaE = fechaE;
    }
   
    public dtoEducation() {
    }

    public dtoEducation(@NotBlank String nombreE, @NotBlank String descripcionE,  @NotBlank String fechaE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.fechaE = fechaE;
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
 
    
    



