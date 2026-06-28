package cibertec.edu.pe.model.medico;

import cibertec.edu.pe.model.enums.DiaSemanaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "disponibilidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disponibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @Enumerated(EnumType.STRING)
    private DiaSemanaEnum diaSemana;

    private LocalTime horaInicio;

    private LocalTime horaFin;
}
