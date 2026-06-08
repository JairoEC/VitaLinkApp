package cibertec.edu.pe.model;

import cibertec.edu.pe.model.constantes.GrupoSanguineo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "paciente_clinico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteClinico {
    @Id
    private Long dni;
    @Enumerated(EnumType.STRING)
    private GrupoSanguineo grupoSanguineo;

    @ManyToMany
    @JoinTable(
            name = "paciente_clinico_alergias",
            joinColumns = @JoinColumn(name = "paciente_clinico_dni"),
            inverseJoinColumns = @JoinColumn(name = "alergia_id")
    )
    private List<Alergias> alergias;

    @ManyToMany
    @JoinTable(
            name = "paciente_clinico_enfermedades_cronicas",
            joinColumns = @JoinColumn(name = "paciente_clinico_dni"),
            inverseJoinColumns = @JoinColumn(name = "enfermedad_cronica_id")
    )
    private EnfermedadesCronicas enfermedadesCronicas;

}
