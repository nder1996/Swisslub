package testswisslub.testswisslub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import testswisslub.testswisslub.entitys.MovimientosDetalles;

@Repository
public interface MovimientoDetallesRepository_JPA extends JpaRepository<MovimientosDetalles, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO movimiento_detalle(id, movimiento_id, item_codigo, cantidad_enviada) VALUES (:id, :movimiento_id, :item_codigo, :cantidad_enviada)")
    void guardarDetalle(@Param("id") Long id, @Param("movimiento_id") Long movimiento_id, @Param("item_codigo") String item_codigo, @Param("cantidad_enviada") Long cantidad_enviada);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE movimiento_detalle SET movimiento_id = :movimiento_id, item_codigo = :item_codigo, cantidad_enviada = :cantidad_enviada WHERE id = :id")
    void actualizarDetalle(@Param("id") Long id, @Param("movimiento_id") Long movimiento_id, @Param("item_codigo") String item_codigo, @Param("cantidad_enviada") Long cantidad_enviada);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM movimiento_detalle WHERE id = :id")
    void eliminarDetalle(@Param("id") Long id);
}
