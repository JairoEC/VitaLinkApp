package cibertec.edu.pe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "alergias")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alergias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alergia;
    private String descripcion;

    @ManyToMany(mappedBy = "alergias")
    private List<PacienteClinico> pacienteClinicos;
}
