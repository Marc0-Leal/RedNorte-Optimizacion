package redNorte.Optimizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import redNorte.Optimizacion.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}