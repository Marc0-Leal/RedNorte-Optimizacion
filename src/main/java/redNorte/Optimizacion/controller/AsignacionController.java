package redNorte.Optimizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import redNorte.Optimizacion.model.Asignacion;
import redNorte.Optimizacion.service.AsignacionService;

@RestController
@RequestMapping("/api/asignacion")
public class AsignacionController {

    @Autowired
    private AsignacionService asignacionService;

    // GET /asignacion — obtener todas las asignaciones
    @GetMapping
    public ResponseEntity<List<Asignacion>> obtenerTodas() {
        return ResponseEntity.ok(asignacionService.obtenerTodas());
    }

    // GET /asignacion/{id} — obtener una asignación por id
    @GetMapping("/{id}")
    public ResponseEntity<Asignacion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(asignacionService.obtenerPorId(id));
    }

    // GET /asignacion/medico/{medicoId} — ver asignaciones de un médico
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Asignacion>> obtenerPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(asignacionService.obtenerPorMedico(medicoId));
    }

    // GET /asignacion/estado/{estado} — filtrar por PENDIENTE, CONFIRMADA, CANCELADA
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Asignacion>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(asignacionService.obtenerPorEstado(estado));
    }

    // POST /asignacion — crear una asignación nueva aplicando las reglas
    @PostMapping
    public ResponseEntity<Asignacion> asignar(
            @RequestParam Long listaEsperaId,
            @RequestParam String prioridad,
            @RequestParam boolean medicoDisponible,
            @RequestParam boolean mismaRegion,
            @RequestParam Long medicoId,
            @RequestParam Long hospitalId) {

        Asignacion nueva = asignacionService.asignar(
                listaEsperaId, prioridad,
                medicoDisponible, mismaRegion,
                medicoId, hospitalId);

        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // PATCH /asignacion/{id}/estado — actualizar solo el estado
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Asignacion> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {

        return ResponseEntity.ok(asignacionService.actualizarEstado(id, nuevoEstado));
    }

    // DELETE /asignacion/{id} — eliminar una asignación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        asignacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}