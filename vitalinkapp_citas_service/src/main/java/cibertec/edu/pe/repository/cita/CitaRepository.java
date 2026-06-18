package cibertec.edu.pe.repository.cita;

import cibertec.edu.pe.dto.CitaResponseDto;
import cibertec.edu.pe.model.cita.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    @Query("SELECT new cibertec.edu.pe.dto.CitaResponseDto(" +
            "c.id, c.estado, c.motivo, c.fechaHora, " + // <-- Faltaba la coma aquí
            "p.nombres, p.correo, p.dni, p.fechaNacimiento, " +
            "m.nombres, m.apellidos, e.nombre) " +
            "FROM Cita c " +
            "JOIN c.paciente p " +
            "JOIN c.medico m " +
            "JOIN m.especialidad e " +
            "WHERE c.id = :id")
    public CitaResponseDto buscarCitaPorId(@Param("id") Long id);
}