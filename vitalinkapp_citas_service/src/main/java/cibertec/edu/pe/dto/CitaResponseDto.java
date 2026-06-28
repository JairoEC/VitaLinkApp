package cibertec.edu.pe.dto;

import cibertec.edu.pe.model.cita.Cita;
import cibertec.edu.pe.model.medico.Medico;
import cibertec.edu.pe.model.paciente.Paciente;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement(name = "cita")
@XmlAccessorType(XmlAccessType.FIELD)
public class CitaResponseDto {
    private Long id;
    private String estado;
    private String motivo;
    private LocalDateTime fechaHora;
    private String nombrePaciente;
    private String correoPaciente;
    private String dniPaciente;
    private LocalDate fechaNacimiento;
    private String nombreMedico;
    private String apellidoMedico;
    private String especialidad;
}
