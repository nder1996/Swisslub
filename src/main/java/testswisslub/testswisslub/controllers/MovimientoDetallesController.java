package testswisslub.testswisslub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.entitys.MovimientosDetalles;
import testswisslub.testswisslub.services.MovimientoDetallesServices;
import testswisslub.testswisslub.services.MovimientoServices;

import java.util.List;

@RestController
@RequestMapping("/movimientoDetalles")
public class MovimientoDetallesController {


    private final MovimientoDetallesServices movimientoDetallesServices;


    @Autowired
    public MovimientoDetallesController(MovimientoDetallesServices movimientoDetallesServices) {
        this.movimientoDetallesServices = movimientoDetallesServices;
    }


    @GetMapping("/getAll")
    public List<MovimientosDetalles> findAll(){
        try {
            return movimientoDetallesServices.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    @GetMapping("/ById/{id}")
    public MovimientosDetalles findById(@PathVariable Long id){
        try {
            return movimientoDetallesServices.buscarMovimientoID(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/ByMovimientoID/{id}")
    public List<MovimientosDetalles> findByMovimentoID(@PathVariable Long id){
        try {
            return movimientoDetallesServices.buscarMovimientoIdMoviento(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/ByMovimientoEstadoXDetalles/{estado}")
    public List<MovimientosDetalles> findByMovimentoID(@PathVariable String estado){
        try {
            return movimientoDetallesServices.buscarMovimientoMovimientoXDetallesEstado(estado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @PostMapping("/crearMovimientoDetalle")
    public ResponseEntity<String> crearMovimientoDetalle(@RequestBody MovimientosDetalles detalles) {
        try {
            System.out.println("Info Detalles id : "+detalles.getId());
            //System.out.println("Info Detalles movimiento_id : "+detalles.getMovimiento().getId());
            System.out.println("Info Detalles  cantidad enviada : "+detalles.getCantidad_enviada());
            System.out.println("Info Detalles  item codigo : "+detalles.getItem_codigo());

            movimientoDetallesServices.crearNewMovimientoDetalles(detalles);
            return ResponseEntity.ok("se guardo con exito el nuevo registro de la tabla movimiento detalles");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hubo un error al momento de guardar el nuevo registro de la tabla movimiento detalles : " + e.getMessage());
        }
    }


    @PutMapping("/actualizarMovimientoDetalle")
    public ResponseEntity<String> updateMovimientoDetalle(@RequestBody MovimientosDetalles detalles) {
        try {
            movimientoDetallesServices.actualizarMovimientoDetalles(detalles);
            return ResponseEntity.ok("el registro de la tabla movimiento detalles se actualizo con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el registro de la tabla movimiento detalles: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimientoDetalles(@PathVariable Long id) {
        movimientoDetallesServices.eliminarMovimientoDetalles(id);
        return ResponseEntity.noContent().build();
    }


}
