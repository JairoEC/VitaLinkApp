package cibertec.edu.pe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.model.Especialidad;

@Repository
public interface EspecialidadRepository
        extends JpaRepository<Especialidad, Long> {
	
	 Optional<Especialidad> findByNombre(String nombre);
}
