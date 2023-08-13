package testswisslub.testswisslub.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.services.MovimientoServices;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {



    @PersistenceContext
    private EntityManager entityManager;

    private final MovimientoServices movimientoService;

    @Autowired
    public MovimientoController(MovimientoServices movimientoService) {
        this.movimientoService = movimientoService;
    }


    @GetMapping("/getAll")
    public List<Movimiento> findAll() {
        return movimientoService.findAll();
    }

}
