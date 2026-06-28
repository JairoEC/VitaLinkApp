package cibertec.edu.pe.repository.paciente_clinico;

import cibertec.edu.pe.model.paciente_clinico.PacienteClinico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteClinicoRepository extends JpaRepository<PacienteClinico, Long> {
    Optional<PacienteClinico> findByPacienteId(Long pacienteId);
}
