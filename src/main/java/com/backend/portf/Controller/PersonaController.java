package com.backend.portf.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.portf.DTO.dtoPersona;
import com.backend.portf.Entity.Persona;
import com.backend.portf.Security.Controller.Mensaje;
import com.backend.portf.Service.ImpPersonaService;


@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired 

    ImpPersonaService personaService;

    @GetMapping("/list")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
        public ResponseEntity<Persona> getById(@PathVariable("id") int id){
            
            if (!personaService.existsById(id))
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
     
            Persona persona = personaService.getOne(id).get();
            return new ResponseEntity<>(persona, HttpStatus.OK);

        }
    
    
       @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtoPersona){
     
       if(!personaService.existsById(id)) 
       return new ResponseEntity<>(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
       
       if(personaService.existsByNombre(dtoPersona.getNombre()) && personaService.getByNombre(dtoPersona.getNombre()).get().getId() != id)
          return new ResponseEntity<>(new Mensaje("Esa nombre ya existe"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(dtoPersona.getNombre())) 
          return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST); 
    
        Persona persona = personaService.getOne(id).get();
        persona.setNombre(dtoPersona.getNombre());
        persona.setApellido((dtoPersona.getApellido()));
        persona.setDescripcion(dtoPersona.getDescripcion());
        persona.setImg(dtoPersona.getImg());
        
        personaService.save(persona);
        return new ResponseEntity<>(new Mensaje("Persona actualizada"), HttpStatus.OK);
        }
      
      
  

}
