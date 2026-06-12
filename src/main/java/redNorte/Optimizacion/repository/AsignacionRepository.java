package redNorte.Optimizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import redNorte.Optimizacion.model.Asignacion;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {

    // Buscar todas las asignaciones de un médico específico
    List<Asignacion> findByMedicoId(Long medicoId);

    // Buscar asignaciones por estado (PENDIENTE, CONFIRMADA, CANCELADA)
    List<Asignacion> findByEstado(String estado);

    // Buscar asignaciones de una lista de espera específica
    List<Asignacion> findByListaEsperaId(Long listaEsperaId);

    // Verificar si ya existe una asignación para una lista de espera
    boolean existsByListaEsperaId(Long listaEsperaId);
}