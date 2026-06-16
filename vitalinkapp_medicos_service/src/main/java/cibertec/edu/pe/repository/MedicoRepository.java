package cibertec.edu.pe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.model.Medico;

@Repository
public interface MedicoRepository
        extends JpaRepository<Medico, Long> {

    List<Medico> findByEspecialidadId(Long id);

    Optional<Medico> findByCmp(String cmp);
}
