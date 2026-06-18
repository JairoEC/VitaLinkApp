package cibertec.edu.pe.repository.medico;

import cibertec.edu.pe.model.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
