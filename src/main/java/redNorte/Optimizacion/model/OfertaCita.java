package redNorte.Optimizacion.model;

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
@Table(name = "oferta_cita")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OfertaCita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long pacienteId;

    @Column(nullable = false)
    private long horarioId;

    @Column(nullable = false, length = 30)
    private String estado;
}
