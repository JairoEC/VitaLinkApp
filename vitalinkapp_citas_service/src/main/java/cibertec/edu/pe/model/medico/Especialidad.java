package cibertec.edu.pe.model.medico;

import cibertec.edu.pe.model.paciente.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "especialidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;
    @OneToMany(mappedBy = "especialidad")
    @JsonIgnoreProperties("especialidad")
    private List<Medico> medicos;
}
