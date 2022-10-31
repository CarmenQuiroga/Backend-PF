package com.backend.portf.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.backend.portf.DTO.dtoEducation;
import com.backend.portf.Entity.Education;
import com.backend.portf.Security.Controller.Mensaje;
import com.backend.portf.Service.SEducation;


@RestController
@RequestMapping("/Education")
@CrossOrigin(origins = "http://localhost:4200")
public class CEducation {

    @Autowired
    SEducation sEducation;


    @GetMapping("/lista")
    public ResponseEntity<List<Education>> list(){
        List<Education> list = sEducation.list();
        return new ResponseEntity<List<Education>>(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducation dtoeduca){
        if (StringUtils.isBlank(dtoeduca.getNombreE())) 
        return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sEducation.existsByNombreE(dtoeduca.getNombreE()))
           return new ResponseEntity<>(new Mensaje("Esa educacion existe"), HttpStatus.BAD_REQUEST);

        Education education = new Education(dtoeduca.getNombreE(), dtoeduca.getDescripcionE(), dtoeduca.getFechaE());
        sEducation.save(education);
        return new ResponseEntity<>(new Mensaje("Educacion fue creada"), HttpStatus.OK);
    }
     @GetMapping("/detail/{id}")
       public ResponseEntity<Education> getById(@PathVariable("id")int id){
        if(!sEducation.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Education education = sEducation.getOne(id).get();
        return new ResponseEntity<>(education,HttpStatus.OK);
       }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEducation.existsById(id)){
            return new ResponseEntity<>(new Mensaje("No existe id"), HttpStatus.NOT_FOUND);
        }
        sEducation.delete(id);
        return new ResponseEntity<>(new Mensaje("Educación eliminada"), HttpStatus.OK);
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducation dtoeduca){
        if(!sEducation.existsById(id)){
        return new ResponseEntity<>(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        }
        if(sEducation.existsByNombreE(dtoeduca.getNombreE()) && sEducation.getByNombreE(dtoeduca.getNombreE()).get().getId() !=id){
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoeduca.getNombreE())){
            return new ResponseEntity<>(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        Education education = sEducation.getOne(id).get();
        education.setNombreE(dtoeduca.getNombreE());
        education.setDescripcionE(dtoeduca.getDescripcionE());
        education.setFechaE(dtoeduca.getFechaE());
        sEducation.save(education);

        return new ResponseEntity<>(new Mensaje("Educacion Actualizada"), HttpStatus.OK);
    
    }
    
}
