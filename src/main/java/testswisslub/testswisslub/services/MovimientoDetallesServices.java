package testswisslub.testswisslub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.entitys.MovimientosDetalles;
import testswisslub.testswisslub.repository.MovimientoDetallesRepository;
import testswisslub.testswisslub.repository.MovimientoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoDetallesServices {

        @Autowired
        MovimientoDetallesRepository movimientoDetallesRepository;

        MovimientosDetalles movimientosDetalles = new MovimientosDetalles();


    public List<MovimientosDetalles> findAll(){
        List<MovimientosDetalles> movimientos_detalles = new ArrayList<>();
        try {
            System.out.println("entro al metodo del services List<MovimientosDetalles> findAll()");
            movimientos_detalles = movimientoDetallesRepository.getAllMovimientoDetalle();
        }catch (Exception e){
            throw new RuntimeException("Hubo un error al momento de buscar todos los movimientos"+e.getMessage());
        }
        return movimientos_detalles;
    }


    public MovimientosDetalles buscarMovimientoID(Long id) throws Exception{
        MovimientosDetalles movimientos_detalles = new MovimientosDetalles();
        try {
            movimientos_detalles = movimientoDetallesRepository.findById(id);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de buscar el registro por id en la tabla movimientos detalles"+e.getMessage());
        }
        return movimientos_detalles;
    }


    public List<MovimientosDetalles> buscarMovimientoIdMoviento(Long id) throws Exception{
        List<MovimientosDetalles> movimientos_detalles = new ArrayList<>();
        try {
            movimientos_detalles = movimientoDetallesRepository.findByIdMovimiento(id);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de buscar el registro por id en la tabla movimientos"+e.getMessage());
        }
        return movimientos_detalles;
    }

    public List<MovimientosDetalles> buscarMovimientoMovimientoXDetallesEstado(String estado) throws Exception{
        List<MovimientosDetalles> movimientos_detalles = new ArrayList<>();
        try {
            System.out.println("entro al services de buscar por estado"+estado);
            movimientos_detalles = movimientoDetallesRepository.findByIdEstadoMovimientoXDetallesEstado(estado);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de buscar el registro por estado en la tabla movimientos y movimientos detalles"+e.getMessage());
        }
        return movimientos_detalles;
    }



}
