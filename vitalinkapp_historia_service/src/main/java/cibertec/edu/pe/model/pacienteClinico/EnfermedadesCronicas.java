package cibertec.edu.pe.model.pacienteClinico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "enfermedades_cronicas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnfermedadesCronicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enfermedadCronica;
    private String descripcion;

    @ManyToMany(mappedBy = "enfermedadesCronicas")
    private List<PacienteClinico> pacienteClinicos;
}
