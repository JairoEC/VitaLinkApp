package cibertec.edu.pe.repository.consulta;

import cibertec.edu.pe.model.consulta.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescripcionRepository extends JpaRepository<Prescripcion, Long>{
}
