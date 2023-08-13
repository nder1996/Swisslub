package testswisslub.testswisslub.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import testswisslub.testswisslub.entitys.Movimiento;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovimientoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Movimiento> getAllMovimiento() throws IllegalStateException{
        List<Movimiento> movimientos = new ArrayList<>();
        try{
            if (entityManager == null) {
                movimientos = new ArrayList<>();
            }else{
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<Movimiento> criteriaQuery = criteriaBuilder.createQuery(Movimiento.class);
                Root<Movimiento> root = criteriaQuery.from(Movimiento.class);
                criteriaQuery.select(root);
                movimientos = entityManager.createQuery(criteriaQuery).getResultList();
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected.");
        }
        return movimientos;
    }

}
