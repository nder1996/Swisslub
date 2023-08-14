package testswisslub.testswisslub.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.entitys.MovimientosDetalles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class MovimientoRepository {


    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    MovimientoRepository_JPA  movimientoQueryRepositoryJPA;

    MovimientoDetallesRepository_JPA movimientoDetallesRepositoryJpa;




    public List<Movimiento> getAllMovimiento(){
        List<Movimiento> movimientos = new ArrayList<>();
       try{
           if (entityManager == null) {
                movimientos = new ArrayList<>();
            }else{
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<Movimiento> criteriaQuery = criteriaBuilder.createQuery(Movimiento.class);
                Root<Movimiento> root = criteriaQuery.from(Movimiento.class);
                criteriaQuery.select(root);
                System.out.println("valor createQuery : "+entityManager.createQuery(criteriaQuery).getResultList());
                movimientos = entityManager.createQuery(criteriaQuery).getResultList();
           }
        }catch (IllegalStateException illegalStateException){
           throw new IllegalStateException("Hubo un error al momento de llamar todos los datos de la tabla movimiento : "+illegalStateException.getMessage());
        }
        System.out.println("valor movimientos : "+movimientos);
        return movimientos;
    }

    @Transactional
    public Movimiento findByID(Long id) throws IllegalStateException{
        Movimiento movimiento = new Movimiento();
        try{
            if (entityManager != null) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<Movimiento> criteriaQuery = criteriaBuilder.createQuery(Movimiento.class);
                Root<Movimiento> root = criteriaQuery.from(Movimiento.class);
                criteriaQuery.select(root);
                criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
                List<Movimiento> resultados = entityManager.createQuery(criteriaQuery).getResultList();
                if (!resultados.isEmpty()) {
                    movimiento = resultados.get(0);
                } else {
                    movimiento = new Movimiento();
                }
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("Hubo un error al buscar el id del registro en la tabla movimiento : "+illegalStateException.getMessage());

        }
        return movimiento;
    }


    @Transactional
    public List<Movimiento> findByEstado(String estado) throws IllegalStateException{
        List<Movimiento> movimiento = new ArrayList<>();
        try{
            if (entityManager != null) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<Movimiento> criteriaQuery = criteriaBuilder.createQuery(Movimiento.class);
                Root<Movimiento> root = criteriaQuery.from(Movimiento.class);
                criteriaQuery.select(root);
                criteriaQuery.where(criteriaBuilder.equal(root.get("estado"), estado));
                List<Movimiento> resultados = entityManager.createQuery(criteriaQuery).getResultList();
                if (!resultados.isEmpty()) {
                    movimiento = resultados;
                } else {
                    movimiento = new ArrayList<>();;
                }
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("Hubo un error al momento de buscar por estado en los datos de la tabla movimiento"+illegalStateException.getMessage());

        }
        return movimiento;
    }


    @Transactional
    public List<Object> findByEstadoMovimientoXDetalles(String estado) throws IllegalStateException{
        TypedQuery<Object> query = null;
        try {
            if (entityManager != null) {
                CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                CriteriaQuery<Object> cq = cb.createQuery(Object.class);
                Root<MovimientosDetalles> movimientoDetallesRoot = cq.from(MovimientosDetalles.class);
                Join<MovimientosDetalles, Movimiento> movimientoJoin = movimientoDetallesRoot.join("movimiento");
                cq.where(cb.equal(movimientoJoin.get("estado"), estado));
                cq.multiselect(
                        movimientoJoin.get("id"),
                        movimientoDetallesRoot.get("id"),
                        movimientoJoin.get("bodega_origen_codigo"),
                        movimientoJoin.get("id_empresa"),
                        movimientoJoin.get("bodega_destino_codigo"),
                        movimientoDetallesRoot.get("item_codigo")
                );
               query = entityManager.createQuery(cq);
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("Hubo un error al momento de relacionar los datos de la tabla movimiento y movimientos detalles por el estado"+illegalStateException.getMessage());
        }
        return query.getResultList();
    }



    @Transactional
    public void saveMovimientosXDetalles(Movimiento movimiento, List<MovimientosDetalles> detalles) {
        try {
            movimientoQueryRepositoryJPA.guardarMovimiento( movimiento.getId(),  movimiento.getId_empresa(), movimiento.getDescripcion(),
                    movimiento.getBodega_origen_codigo(),  movimiento.getBodega_destino_codigo(), movimiento.getFecha_creacion(), movimiento.getFecha_entrega(),
                    movimiento.getEstado());
            for (MovimientosDetalles detalle : detalles) {
                detalle.setMovimiento(movimiento);
                movimientoDetallesRepositoryJpa.guardarDetalle(detalle.getId(), movimiento.getId(),  detalle.getItem_codigo(),  detalle.getCantidad_enviada()); // Guardar cada detalle
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error al guardar el encabezado y detalles: " + e.getMessage(), e);
        }
    }


    @Transactional
    public void updateMovimiento(Movimiento movimiento) {
            try {
                movimientoQueryRepositoryJPA.actualizarMovimiento( movimiento.getId(),  movimiento.getId_empresa(), movimiento.getDescripcion(),
                        movimiento.getBodega_origen_codigo(),  movimiento.getBodega_destino_codigo(), movimiento.getFecha_creacion(), movimiento.getFecha_entrega(),
                        movimiento.getEstado());
            }catch (Exception  e){
                throw new IllegalStateException("Hubo un error al momento de atualizar los datos " + e.getMessage(), e);
            }
    }


    @Transactional
    public void deleteMovimiento(Long id) {
        try {
            movimientoQueryRepositoryJPA.eliminarMovimiento(id);
        }catch (Exception  e){
            throw new IllegalStateException("Hubo un error al momento de borrar el id en la tabla movimientos " + e.getMessage(), e);
        }
    }

    @Transactional
    public void createMovimiento(Movimiento movimiento){
        try {
            movimientoQueryRepositoryJPA.guardarMovimiento( movimiento.getId(),  movimiento.getId_empresa(), movimiento.getDescripcion(),
                    movimiento.getBodega_origen_codigo(),  movimiento.getBodega_destino_codigo(), movimiento.getFecha_creacion(), movimiento.getFecha_entrega(),
                    movimiento.getEstado());
        }catch (Exception  e){
            throw new IllegalStateException("Hubo un error al momento de borrar el id en la tabla movimientos " + e.getMessage(), e);
        }
    }

}
