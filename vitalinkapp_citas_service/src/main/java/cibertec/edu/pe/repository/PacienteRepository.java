package cibertec.edu.pe.repository;

import cibertec.edu.pe.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
