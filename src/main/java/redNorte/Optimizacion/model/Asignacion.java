package redNorte.Optimizacion.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "asignacion")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Referencia a la lista de espera que fue procesada
    @Column(name = "lista_espera_id", nullable = false)
    private Long listaEsperaId;

    // Referencia al médico asignado
    @Column(name = "medico_id", nullable = false)
    private Long medicoId;

    // Referencia al hospital donde quedó la cita
    @Column(name = "hospital_id", nullable = false)
    private Long hospitalId;

    // Fecha en que se realizó la asignación automática
    @Column(name = "fecha_asignacion", nullable = false)
    private Date fechaAsignacion;

    // Puntaje final calculado por las reglas (para trazabilidad)
    @Column(name = "puntaje", nullable = false)
    private Integer puntaje;

    // Estado de la asignación: PENDIENTE, CONFIRMADA, CANCELADA
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
}
