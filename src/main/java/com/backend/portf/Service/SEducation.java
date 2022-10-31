package com.backend.portf.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.portf.Entity.Education;
import com.backend.portf.Repository.REducation;

@Service
@Transactional
public class SEducation {
    @Autowired
    REducation rEducacion;

    public List<Education> list(){
        return rEducacion.findAll();
    }
     public Optional<Education> getOne(int id){
        return rEducacion.findById(id);
}

  public Optional<Education> getByNombreE(String nombreE){
    return rEducacion.findByNombreE(nombreE);
  }

  public void save (Education educacion){
    rEducacion.save(educacion);
  }

  public void delete(int id){
    rEducacion.deleteById(id);
  }
  public boolean existsById(int id){
    return rEducacion.existsById(id);
  }

  public boolean existsByNombreE(String nombreE){
    return rEducacion.existsByNombreE(nombreE);
  }
}
