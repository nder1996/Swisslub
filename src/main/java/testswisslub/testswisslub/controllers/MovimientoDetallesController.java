package testswisslub.testswisslub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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




}
