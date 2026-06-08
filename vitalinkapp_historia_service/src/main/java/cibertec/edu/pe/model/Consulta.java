package cibertec.edu.pe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dniPaciente;
    private Long medicoId;//FK EXTERNO
    private Long citaId;//FK EXTERNO
    private LocalDateTime fechaAtencion;

    private LocalDateTime fechaCreacion;

    @OneToOne
    @JoinColumn(name = "notasClinicas_id")
    private NotasClinicas notasClinicas;

}
