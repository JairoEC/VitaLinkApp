package cibertec.edu.pe.repository.paciente;

import cibertec.edu.pe.model.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
