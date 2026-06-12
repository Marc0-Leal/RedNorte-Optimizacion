package redNorte.Optimizacion.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redNorte.Optimizacion.model.Asignacion;
import redNorte.Optimizacion.repository.AsignacionRepository;

@Service
public class AsignacionService {

    @Autowired
    private AsignacionRepository asignacionRepository;

    // ─── Métodos CRUD base ───────────────────────────────────────────────────

    public List<Asignacion> obtenerTodas() {
        return asignacionRepository.findAll();
    }

    public Asignacion obtenerPorId(Long id) {
        return asignacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignacion no encontrada con id: " + id));
    }

    public List<Asignacion> obtenerPorMedico(Long medicoId) {
        return asignacionRepository.findByMedicoId(medicoId);
    }

    public List<Asignacion> obtenerPorEstado(String estado) {
        return asignacionRepository.findByEstado(estado);
    }

    public void eliminar(Long id) {
        asignacionRepository.deleteById(id);
    }

    // ─── Lógica de asignación ────────────────────────────────────────────────

    /**
     * Asigna automáticamente un médico y hospital a una entrada de lista de espera.
     * Calcula un puntaje basado en tres criterios:
     *   - Prioridad del paciente   (hasta 50 puntos)
     *   - Disponibilidad del médico (hasta 30 puntos)
     *   - Hospital en misma región  (hasta 20 puntos)
     */
    public Asignacion asignar(Long listaEsperaId, String prioridad,
                              boolean medicoDisponible, boolean mismaRegion,
                              Long medicoId, Long hospitalId) {

        // Verificar que no exista ya una asignación para esta lista de espera
        if (asignacionRepository.existsByListaEsperaId(listaEsperaId)) {
            throw new RuntimeException("Ya existe una asignacion para la lista de espera id: " + listaEsperaId);
        }

        int puntaje = 0;

        // Regla 1 — Prioridad del paciente (50 puntos máx)
        puntaje += calcularPuntajePrioridad(prioridad);

        // Regla 2 — Disponibilidad del médico (30 puntos máx)
        puntaje += calcularPuntajeDisponibilidad(medicoDisponible);

        // Regla 3 — Hospital en la misma región que el cliente (20 puntos máx)
        puntaje += calcularPuntajeComuna(mismaRegion);

        // Crear y guardar la asignación
        Asignacion asignacion = new Asignacion();
        asignacion.setListaEsperaId(listaEsperaId);
        asignacion.setMedicoId(medicoId);
        asignacion.setHospitalId(hospitalId);
        asignacion.setFechaAsignacion(Date.valueOf(LocalDate.now()));
        asignacion.setPuntaje(puntaje);
        asignacion.setEstado("PENDIENTE");

        return asignacionRepository.save(asignacion);
    }

    // ─── Reglas de negocio ───────────────────────────────────────────────────

    // Regla 1: Prioridad — Alta=50, Media=30, Baja=10
    private int calcularPuntajePrioridad(String prioridad) {
        switch (prioridad.toUpperCase()) {
            case "ALTA":   return 50;
            case "MEDIA":  return 30;
            case "BAJA":   return 10;
            default:       return 0;
        }
    }

    // Regla 2: Disponibilidad — médico libre suma 30, ocupado suma 0
    private int calcularPuntajeDisponibilidad(boolean medicoDisponible) {
        return medicoDisponible ? 30 : 0;
    }

    // Regla 3: Comuna/Región — mismo sector suma 20, distinto suma 0
    private int calcularPuntajeComuna(boolean mismaRegion) {
        return mismaRegion ? 20 : 0;
    }

    // ─── Actualizar estado ───────────────────────────────────────────────────

    public Asignacion actualizarEstado(Long id, String nuevoEstado) {
        Asignacion asignacion = obtenerPorId(id);
        asignacion.setEstado(nuevoEstado);
        return asignacionRepository.save(asignacion);
    }
}