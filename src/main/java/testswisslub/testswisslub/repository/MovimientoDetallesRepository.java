package testswisslub.testswisslub.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.entitys.MovimientosDetalles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovimientoDetallesRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MovimientoDetallesRepository_JPA movimientoDetallesRepositoryJpa;



    public List<MovimientosDetalles> getAllMovimientoDetalle(){
        List<MovimientosDetalles> movimientos_detalles = new ArrayList<>();
        try{
            if (entityManager == null) {
                movimientos_detalles = new ArrayList<>();
                System.out.println("entro al metodo del repository List<MovimientosDetalles> getAllMovimientoDetalle() nullo");
            }else{

                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<MovimientosDetalles> criteriaQuery = criteriaBuilder.createQuery(MovimientosDetalles.class);
                Root<MovimientosDetalles> root = criteriaQuery.from(MovimientosDetalles.class);
                criteriaQuery.select(root);
                System.out.println("valor createQuery : "+entityManager.createQuery(criteriaQuery).getResultList());
                movimientos_detalles = entityManager.createQuery(criteriaQuery).getResultList();
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected.");
        }
        //System.out.println("valor movimientos : "+movimientos_detalles);
        return movimientos_detalles;
    }



    @Transactional
    public MovimientosDetalles getAllMovimientoDetalle(Long id) throws IllegalStateException{
        MovimientosDetalles movimiento_detalles = new MovimientosDetalles();
        try{
            if (entityManager != null) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<MovimientosDetalles> criteriaQuery = criteriaBuilder.createQuery(MovimientosDetalles.class);
                Root<MovimientosDetalles> root = criteriaQuery.from(MovimientosDetalles.class);
                criteriaQuery.select(root);
                criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
                List<MovimientosDetalles> resultados = entityManager.createQuery(criteriaQuery).getResultList();
                if (!resultados.isEmpty()) {
                    movimiento_detalles = resultados.get(0);
                } else {
                    movimiento_detalles = new MovimientosDetalles();
                }
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());

        }
        return movimiento_detalles;
    }



    @Transactional
    public List<MovimientosDetalles> findByIdMovimiento(Long id_movimiento) throws IllegalStateException{
        List<MovimientosDetalles> movimiento_detalle = new ArrayList<>();
        try{
            if (entityManager != null) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<MovimientosDetalles> criteriaQuery = criteriaBuilder.createQuery(MovimientosDetalles.class);
                Root<MovimientosDetalles> root = criteriaQuery.from(MovimientosDetalles.class);
                criteriaQuery.select(root);
                criteriaQuery.where(criteriaBuilder.equal(root.get("movimiento").get("id"), id_movimiento));
                List<MovimientosDetalles> resultados = entityManager.createQuery(criteriaQuery).getResultList();
                if (!resultados.isEmpty()) {
                    movimiento_detalle = resultados;
                } else {
                    movimiento_detalle = new ArrayList<>();
                }
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());

        }
        return movimiento_detalle;
    }


    @Transactional
    public MovimientosDetalles findById(Long id) throws IllegalStateException{
        MovimientosDetalles movimiento_detalle = new MovimientosDetalles();
        try{
            if (entityManager != null) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<MovimientosDetalles> criteriaQuery = criteriaBuilder.createQuery(MovimientosDetalles.class);
                Root<MovimientosDetalles> root = criteriaQuery.from(MovimientosDetalles.class);
                criteriaQuery.select(root);
                criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
                List<MovimientosDetalles> resultados = entityManager.createQuery(criteriaQuery).getResultList();
                if (!resultados.isEmpty()) {
                    movimiento_detalle = resultados.get(0);
                } else {
                    movimiento_detalle = new MovimientosDetalles();;
                }
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());

        }
        return movimiento_detalle;
    }


    @Transactional
    public List<MovimientosDetalles> findByIdEstadoMovimientoXDetallesEstado(String estado) throws IllegalStateException{
        List<MovimientosDetalles> movimiento_detalle = new ArrayList<>();
        try{
            if (entityManager != null) {
                CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                CriteriaQuery<MovimientosDetalles> cq = cb.createQuery(MovimientosDetalles.class);
                Root<MovimientosDetalles> movimientoDetalles = cq.from(MovimientosDetalles.class);
                cq.select(movimientoDetalles);
                Join<MovimientosDetalles, Movimiento> movimiento = movimientoDetalles.join("movimiento");
                cq.where(cb.equal(movimiento.get("estado"), estado));
                TypedQuery<MovimientosDetalles> query = entityManager.createQuery(cq);
                List<MovimientosDetalles> resultados = query.getResultList();
                if (!resultados.isEmpty()) {
                    movimiento_detalle = resultados;
                } else {
                    movimiento_detalle = new ArrayList<>();
                }
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());

        }
        return movimiento_detalle;
    }


    @Transactional
    public void updateMovimientoDetalles(MovimientosDetalles detalle) {
        try {
            movimientoDetallesRepositoryJpa.actualizarDetalle(detalle.getId(), detalle.getMovimiento().getId(),  detalle.getItem_codigo(),  detalle.getCantidad_enviada()); // Guardar cada detalle
        }catch (Exception  e){
            throw new IllegalStateException("Hubo un error al momento de atualizar los datos en la tabla movimiento detalles " + e.getMessage(), e);
        }
    }


    @Transactional
    public void deleteMovimientoDetalles(Long id) {
        try {
            movimientoDetallesRepositoryJpa.eliminarDetalle(id);
        }catch (Exception  e){
            throw new IllegalStateException("Hubo un error al momento de borrar el id en la tabla movimientos detalles " + e.getMessage(), e);
        }
    }

    @Transactional
    public void createMovimiento(MovimientosDetalles detalle){
        try {
            movimientoDetallesRepositoryJpa.guardarDetalle(detalle.getId(), detalle.getMovimiento().getId(),  detalle.getItem_codigo(),  detalle.getCantidad_enviada());
        }catch (Exception  e){
            throw new IllegalStateException("Hubo un error al momento de borrar el id en la tabla movimientos " + e.getMessage(), e);
        }
    }
}
