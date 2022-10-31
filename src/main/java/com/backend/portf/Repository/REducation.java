package com.backend.portf.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.portf.Entity.Education;

@Repository
public interface REducation extends JpaRepository<Education, Integer>{
    public Optional<Education> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);  
}
