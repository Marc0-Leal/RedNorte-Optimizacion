package redNorte.Optimizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import redNorte.Optimizacion.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}