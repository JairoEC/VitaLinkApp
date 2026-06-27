package cibertec.edu.pe.repository.consulta;

import cibertec.edu.pe.model.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
    List<Consulta> findByPacienteClinico_PacienteIdOrderByFechaAtencionDesc(Long pacienteId);
}
