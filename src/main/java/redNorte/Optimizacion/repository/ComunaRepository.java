package redNorte.Optimizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import redNorte.Optimizacion.model.Comuna;

public interface ComunaRepository extends JpaRepository<Comuna, Long> {
}