package cibertec.edu.pe.model.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "prescripcion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prescripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Consulta consulta;

    @OneToMany(mappedBy = "prescripcion")
    private List<DetalleMedicamento> detalleMedicamentos;
}
