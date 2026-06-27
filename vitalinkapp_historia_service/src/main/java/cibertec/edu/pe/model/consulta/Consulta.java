package cibertec.edu.pe.model.consulta;

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
    private Long historiaClinicaId;
    private Long medicoId;
    private Long citaId;
    @ManyToOne
    @JoinColumn(name="paciente_id")
    private Long pacienteId;
    private LocalDate fechaAtencion;
    private String motivoConsulta;
    private String observaciones;

    private LocalDateTime fechaCreacion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescripcion_id", referencedColumnName = "id")
    private Prescripcion prescripcion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diagnostico_id", referencedColumnName = "id")
    private Diagnostico diagnostico;
}
