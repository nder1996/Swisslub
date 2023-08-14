package testswisslub.testswisslub.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.entitys.MovimientosDetalles;
import testswisslub.testswisslub.request.MovimientoRequest;
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

    @GetMapping("/findEstadoMovimientoXDetalles/{estado}")
    public List<Object> findByEstadoMovimienoXDetalles(@PathVariable String estado){
        try {
            return movimientoService.BuscarEstadoXMovimientoXMovimiento(estado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/crearMovimientoXDetalles")
    public ResponseEntity<String> crearMovimientoXDetalles(@RequestBody MovimientoRequest request) {
        try {
            movimientoService.guardarMovimientoXDetalles(request);
            return ResponseEntity.ok("Movimiento y detalles guardados exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el movimiento y detalles: " + e.getMessage());
        }
    }

    @PostMapping("/crearMovimiento")
    public ResponseEntity<String> crearMovimiento(@RequestBody Movimiento movimiento) {
        try {
            movimientoService.crearNewMovimiento(movimiento);
            return ResponseEntity.ok("Movimiento guardados exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el movimiento y detalles: " + e.getMessage());
        }
    }


    @PutMapping("/actualizarMovimiento")
    public ResponseEntity<String> updateMovimiento(@RequestBody Movimiento movimiento) {
        try {
            movimientoService.actualizarMovimiento(movimiento);
            return ResponseEntity.ok("Movimiento actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el registro: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }





}
