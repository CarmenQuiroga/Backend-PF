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

import com.backend.portf.DTO.dtoSkill;
import com.backend.portf.Entity.Skill;
import com.backend.portf.Security.Controller.Mensaje;
import com.backend.portf.Service.SSkills;

@RestController
@CrossOrigin (origins = "http://localhost4200")
@RequestMapping("/Skill")
public class CSkills {
    @Autowired
    SSkills sSkills;
    @GetMapping("/list")
    public ResponseEntity<List<Skill>> list(){
        List<Skill> list = sSkills.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
        public ResponseEntity<Skill> getById(@PathVariable("id") int id){
            
            if (!sSkills.existsById(id))
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
     
            Skill skill = sSkills.getOne(id).get();
            return new ResponseEntity<>(skill, HttpStatus.OK);

        }
    
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkill dtoSkill){
        if (StringUtils.isBlank(dtoSkill.getNombreSkill())) 
        return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sSkills.existsByNombre(dtoSkill.getNombreSkill()))
           return new ResponseEntity<>(new Mensaje("Esa skill existe"), HttpStatus.BAD_REQUEST);

        Skill skill = new Skill( dtoSkill.getNombreSkill(), dtoSkill.getPorcentaje());
        sSkills.save(skill);

        return new ResponseEntity<>(new Mensaje("Skill agregada"), HttpStatus.OK);
    }
       @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkill dtoSkill){
     
       if(!sSkills.existsById(id)) 
       return new ResponseEntity<>(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
       
       if(sSkills.existsByNombre(dtoSkill.getNombreSkill()) && sSkills.getByNombreSkill(dtoSkill.getNombreSkill()).get().getId() != id)
          return new ResponseEntity<>(new Mensaje("Esa Skill ya existe"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(dtoSkill.getNombreSkill())) 
          return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST); 
    
        Skill skill = sSkills.getOne(id).get();
        skill.setNombreSkill(dtoSkill.getNombreSkill());
        skill.setPorcentaje((dtoSkill.getPorcentaje()));
        
        
        sSkills.save(skill);
        return new ResponseEntity<>(new Mensaje("Skills actualizada"), HttpStatus.OK);
        }
      
        @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
         
        if (!sSkills.existsById(id))
        return new ResponseEntity<>(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);

        sSkills.delete(id);
        return new ResponseEntity<>( new Mensaje("Skill borrada"), HttpStatus.OK);
    }    


}
