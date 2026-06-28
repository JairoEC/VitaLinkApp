package cibertec.edu.pe.model.paciente_clinico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "paciente_clinico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteClinico {
    @Id
    private Long pacienteId;
    private Integer alturaCm;
    private Integer pesoKg;
    private LocalDateTime fechaActualizacion;

}
