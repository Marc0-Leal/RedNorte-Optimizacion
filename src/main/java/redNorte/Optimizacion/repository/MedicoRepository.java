package redNorte.Optimizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import redNorte.Optimizacion.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

        List<Medico> findByHospitalId(Long hospitalId);
}