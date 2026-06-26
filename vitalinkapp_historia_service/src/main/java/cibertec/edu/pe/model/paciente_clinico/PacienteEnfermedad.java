package cibertec.edu.pe.model.paciente_clinico;

import jakarta.persistence.*;

@Entity
@Table(name = "paciente_enfermedad")
public class PacienteEnfermedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enfermedad_id")
    private EnfermedadesCronicas enfermedadesCronicas;
    @ManyToOne
    @JoinColumn(name = "paciente_clinico_id")
    private PacienteClinico pacienteClinico;
}
