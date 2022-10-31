package com.backend.portf.Entity;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private String nombreE;
    private String descripcionE;
    private String  fechaE;
   
   
    public Education() {
    }


    public Education(String nombreE, String descripcionE, String fechaE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.fechaE = fechaE;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
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


    public String getFechaE() {
        return fechaE;
    }


    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }

    
}
