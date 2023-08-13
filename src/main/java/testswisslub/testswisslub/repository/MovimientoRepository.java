package testswisslub.testswisslub.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import testswisslub.testswisslub.dto.MovimientoDTO;
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
           throw new IllegalStateException("EntityManager is null. Make sure it's properly injected.");
        }
        System.out.println("valor movimientos : "+movimientos);
        return movimientos;
    }


    /*@Transactional
    public void updateMovimiento(Movimiento movimiento , Long id) throws IllegalStateException{
        try{
            if (entityManager != null) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaUpdate<Movimiento> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Movimiento.class);
                Root<Movimiento> root = criteriaUpdate.from(Movimiento.class);
                criteriaUpdate.set(root, movimiento)
                        .where(criteriaBuilder.equal(root.get("id"), id));
                int updatedCount = entityManager.createQuery(criteriaUpdate).executeUpdate();
                System.out.println("Registros actualizados: " + updatedCount);
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());
        }
    }*/

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
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());

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
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());

        }
        return movimiento;
    }


    /*@Transactional
    public void deleteMovimiento(Long id) throws IllegalStateException{
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Movimiento> criteriaDelete = criteriaBuilder.createCriteriaDelete(Movimiento.class);
            Root<Movimiento> root = criteriaDelete.from(Movimiento.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
            int deletedCount = entityManager.createQuery(criteriaDelete).executeUpdate();
            System.out.println("Registros eliminados: " + deletedCount);
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());
        }
    }

    @Transactional
    public void crearNuevoMovimiento(Movimiento nuevoMovimiento) throws IllegalStateException{
        try {

        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());
        }
    }*/

    @Transactional
    public List<Object[]> findByEstadoMovimientoXDetalles(String estado) throws IllegalStateException{
        TypedQuery<Object[]> query = null;
        try {
            if (entityManager != null) {
                CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
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
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected."+illegalStateException.getMessage());
        }
        return query.getResultList();
    }











}
