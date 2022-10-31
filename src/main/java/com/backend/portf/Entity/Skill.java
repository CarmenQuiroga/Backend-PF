package com.backend.portf.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



 @Entity
public class Skill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nombreSkill;
  private int porcentaje;

  
public Skill() {
}


public Skill( String nombreSkill,  int porcentaje) {
    this.nombreSkill = nombreSkill;
    this.porcentaje = porcentaje;
}

  public int getId(){
    return id;
  }
 
  public void setId(int id){
    this.id = id;
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

