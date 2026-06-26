package cibertec.edu.pe.api.dto.requestdto;

import cibertec.edu.pe.model.consulta.Prescripcion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultaCreateDto {
    private Long historiaClinicaId;
    private Long medicoId;
    private Long citaId;
    private LocalDate fechaAtencion;
    private String motivoConsulta;
    private String observaciones;

}
