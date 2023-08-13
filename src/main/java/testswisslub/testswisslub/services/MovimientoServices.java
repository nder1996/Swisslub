package testswisslub.testswisslub.services;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.repository.MovimientoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoServices {

    MovimientoRepository movimientoRepository = new MovimientoRepository();

    @PersistenceContext
    private EntityManager entityManager;


    public List<Movimiento> findAll() throws IllegalStateException{
        List<Movimiento>movimientos = new ArrayList<>();
        try {
            if (entityManager == null) {
                movimientos = new ArrayList<>();
            }else{
                movimientos = movimientoRepository.getAllMovimiento();
            }
        }catch (IllegalStateException illegalStateException){
            throw new IllegalStateException("EntityManager is null. Make sure it's properly injected.");
        }
        return movimientos;
    }
}
