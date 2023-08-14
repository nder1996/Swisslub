package testswisslub.testswisslub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import testswisslub.testswisslub.entitys.Movimiento;

import java.util.Date;

@Repository
public interface MovimientoRepository_JPA extends JpaRepository<Movimiento, Long> {


    @Modifying
    @Query(value = "INSERT INTO movimiento (id, id_empresa, descripcion, bodega_origen_codigo, bodega_destino_codigo, fecha_creacion, fecha_entrega, estado) VALUES (:id, :id_empresa, :descripcion, :bodega_origen_codigo, :bodega_destino_codigo, :fecha_creacion, :fecha_entrega, :estado)", nativeQuery = true)
    void guardarMovimiento(@Param("id") Long id, @Param("id_empresa") Long id_empresa, @Param("descripcion") String descripcion, @Param("bodega_origen_codigo") String bodega_origen_codigo, @Param("bodega_destino_codigo") String bodega_destino_codigo, @Param("fecha_creacion") Date fecha_creacion, @Param("fecha_entrega") Date fecha_entrega, @Param("estado") String estado);


    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO movimiento_detalle(id, movimiento_id, item_codigo, cantidad_enviada) VALUES (:id, :movimiento_id, :item_codigo, :cantidad_enviada)")
    void guardarDetalle(@Param("id") Long id, @Param("movimiento_id") Long v, @Param("item_codigo") String item_codigo, @Param("cantidad_enviada") Long cantidad_enviada);

}
