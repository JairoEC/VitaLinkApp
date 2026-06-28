package cibertec.edu.pe.repository.medico;

import cibertec.edu.pe.model.enums.DiaSemanaEnum;
import cibertec.edu.pe.model.medico.Disponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long> {

    @Query("SELECT COUNT(d) > 0 FROM Disponibilidad d " +
            "WHERE d.medico.id = :medicoId " +
            "AND d.diaSemana = :dia " +
            "AND :horaSolicitada >= d.horaInicio " +
            "AND :horaSolicitada < d.horaFin")
    boolean existsByMedicoAndDiaAndHora(@Param("medicoId") Long medicoId,
                                        @Param("dia") DiaSemanaEnum dia,
                                        @Param("horaSolicitada") LocalTime horaSolicitada);


    List<Disponibilidad> findByMedicoIdAndDiaSemana(Long medicoId,
                                                    DiaSemanaEnum dia);
}
