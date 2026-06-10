package redNorte.Optimizacion.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paciente_espera")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class PacienteEspera {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long pacienteId;

    @Column(nullable = false, length = 30)
    private String especialidad;

    @Column(nullable = false, length = 30)
    private String urgencia;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false, length = 10)
    private Integer prioridad;
}