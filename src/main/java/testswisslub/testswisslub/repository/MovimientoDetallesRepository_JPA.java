package testswisslub.testswisslub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testswisslub.testswisslub.entitys.MovimientosDetalles;

@Repository
public interface MovimientoDetallesRepository_JPA extends JpaRepository<MovimientosDetalles, Long> {


}
