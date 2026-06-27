package cibertec.edu.pe.repository.cita;

import cibertec.edu.pe.dto.CitaResponseDto;
import cibertec.edu.pe.model.cita.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    @Query("SELECT new cibertec.edu.pe.dto.CitaResponseDto(" +
            "c.id, c.estado, c.motivo, c.fechaHora, " +
            "p.nombres, p.correo, p.dni, p.fechaNacimiento, " +
            "m.nombres, m.apellidos, e.nombre) " +
            "FROM Cita c " +
            "JOIN c.paciente p " +
            "JOIN c.medico m " +
            "JOIN m.especialidad e " +
            "WHERE c.id = :id")
    public CitaResponseDto buscarCitaPorId(@Param("id") Long id);

    boolean existsByMedicoIdAndFechaHora(Long medicoId, LocalDateTime fechaHora);

    List<Cita> findByMedicoIdAndFechaHoraBetween(Long medicoId, LocalDateTime inicio, LocalDateTime fin);
}