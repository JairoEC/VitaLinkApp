package cibertec.edu.pe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notas_clinicas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotasClinicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "notasClinicas")
    private Consulta consulta;

    private String razonVisita;

    private Double alturaCm;
    private Double pesoKg;
    private Double presion;
}
