package com.backend.portf.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.portf.Entity.Skill;
import com.backend.portf.Repository.RSkills;

@Transactional
@Service
public class SSkills {
    @Autowired

    RSkills rskills;


    public List<Skill> list(){
        return rskills.findAll();

    }

    public Optional<Skill> getOne(int id){
        return rskills.findById(id);
    }

    public Optional<Skill> getByNombreSkill(String nombreSkill){
        return rskills.findByNombreSkill(nombreSkill);

    }
    
    public void save(Skill skill){
        rskills.save(skill);
    }

    public void delete (int id){
        rskills.deleteById(id);
    }

    public boolean existsById(int id){
        return rskills.existsById(id);
    }

    public boolean existsByNombre(String nombreSkill){
        return rskills.existsByNombreSkill(nombreSkill);
    }
}
