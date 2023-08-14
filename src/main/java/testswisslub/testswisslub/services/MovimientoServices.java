package testswisslub.testswisslub.services;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.entitys.MovimientosDetalles;
import testswisslub.testswisslub.repository.MovimientoRepository;
import testswisslub.testswisslub.request.MovimientoRequest;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovimientoServices {

    @Autowired
    MovimientoRepository movimientoRepository;

    Movimiento movimiento = new Movimiento();



    @PersistenceContext
    private EntityManager entityManager;


    public List<Movimiento> findAll(){
        List<Movimiento> movimientos = new ArrayList<>();
        try {
            movimientos = movimientoRepository.getAllMovimiento();
        }catch (Exception e){
            throw new RuntimeException("Hubo un error al momento de buscar todos los movimientos"+e.getMessage());
        }
        return movimientos;
    }


    public Movimiento buscarMovimientoID(Long id) throws Exception{
        Movimiento movimiento = new Movimiento();
        try {
            movimiento = movimientoRepository.findByID(id);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de buscar el registro por id en la tabla movimientos"+e.getMessage());
        }
        return movimiento;
    }


    public List<Movimiento> buscarEstado(String estado) throws Exception{
        List<Movimiento> movimiento =new ArrayList<>();
        try {
            movimiento = movimientoRepository.findByEstado(estado);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de buscar el registro por id en la tabla movimientos"+e.getMessage());
        }
        return movimiento;
    }




    public List<Object> BuscarEstadoXMovimientoXMovimiento(String estado){
        List<Object> movimientoObject =new ArrayList<>();
        try {
            movimientoObject = movimientoRepository.findByEstadoMovimientoXDetalles(estado);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de buscar el registro por id en la tabla movimientos"+e.getMessage());
        }
        return movimientoObject;
    }

    @Transactional
    public void guardarMovimientoXDetalles(MovimientoRequest request){
        try {

            Movimiento movimiento = new Movimiento();
            List<MovimientosDetalles> movimientosDetalles = new ArrayList<>();
            movimiento.setId(request.getMovimiento().getId());
            movimiento.setEstado(request.getMovimiento().getEstado());
            movimiento.setFecha_entrega(request.getMovimiento().getFecha_entrega());
            movimiento.setFecha_creacion(request.getMovimiento().getFecha_creacion());
            movimiento.setId_empresa(request.getMovimiento().getId_empresa());
            movimiento.setDescripcion(request.getMovimiento().getDescripcion());
            movimiento.setBodega_origen_codigo(request.getMovimiento().getBodega_origen_codigo());
            movimiento.setBodega_destino_codigo(request.getMovimiento().getBodega_destino_codigo());
            for (MovimientosDetalles detalle : request.getDetalles()) {
                MovimientosDetalles nuevoDetalle = new MovimientosDetalles();
                nuevoDetalle.setId(detalle.getId());
                nuevoDetalle.setMovimiento(movimiento);
                nuevoDetalle.setCantidad_enviada(detalle.getCantidad_enviada());
                movimientosDetalles.add(nuevoDetalle);
            }
            System.out.println("movimiento : "+movimiento+" detalles : "+movimientosDetalles);
            movimientoRepository.saveMovimientosXDetalles(movimiento,movimientosDetalles);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de guardar el movimiento con sus detalles"+e.getMessage());
        }

    }




}
