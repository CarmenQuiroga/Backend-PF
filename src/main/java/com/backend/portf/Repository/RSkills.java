package com.backend.portf.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.portf.Entity.Skill;


@Repository
public interface RSkills  extends JpaRepository<Skill, Integer>{
     public Optional<Skill> findByNombreSkill(String nombreSkill);
     public boolean existsByNombreSkill(String nombreSkill);  
}
