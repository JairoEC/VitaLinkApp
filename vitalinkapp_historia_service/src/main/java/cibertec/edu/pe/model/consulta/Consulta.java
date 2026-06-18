package cibertec.edu.pe.model.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Data
@AllArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long historiaClinicaId;
    private Long medicoId;
    private Long citaId;
    private LocalDate fechaAtencion;
    private String motivoConsulta;
    private String observaciones;

    private LocalDateTime fechaCreacion;
}
