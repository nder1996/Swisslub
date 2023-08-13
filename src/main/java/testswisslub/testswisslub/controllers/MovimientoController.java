package testswisslub.testswisslub.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testswisslub.testswisslub.dto.MovimientoDTO;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.services.MovimientoServices;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {


    private final MovimientoServices movimientoService;


    @Autowired
    public MovimientoController(MovimientoServices movimientoService) {
        this.movimientoService = movimientoService;
    }


    @GetMapping("/getAll")
    public List<Movimiento> findAll(){
        try {
            return movimientoService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

   @GetMapping("/ById/{id}")
    public Movimiento findById(@PathVariable Long id){
        try {
            return movimientoService.buscarMovimientoID(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/ByEstado/{estado}")
    public List<Movimiento> findByEstado(@PathVariable String estado){
        try {
            return movimientoService.buscarEstado(estado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





/*
    @PostMapping
    public void crearMovimientoDetalles(@RequestBody MovimientoDTO movimiento) {
        try {
            movimientoService.crearMovimiento(movimiento);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*@PutMapping("/{id}")
    public void actualizarMovimientoDetalles(@PathVariable Long id, @RequestBody MovimientoDTO movimiento) {
        try {
            movimientoService.actualizarMovimiento(movimiento,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void borrarMovimientoDetalles(@PathVariable Long id) {
        try {
            movimientoService.eliminarById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

}
