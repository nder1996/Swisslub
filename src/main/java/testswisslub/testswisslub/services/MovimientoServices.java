package testswisslub.testswisslub.services;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testswisslub.testswisslub.dto.MovimientoDTO;
import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.repository.MovimientoRepository;
import testswisslub.testswisslub.repository.MovimientoRepository_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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


   /* public void actualizarMovimiento(MovimientoDTO movimientoDTO , Long id) throws Exception{
        try {
            Movimiento movimiento1 = new Movimiento();
            movimiento1.setId(movimientoDTO.getId());
            movimiento1.setDescripcion(movimientoDTO.getDescripcion());
            movimiento1.setEstado(movimientoDTO.getEstado());
            movimiento1.setId_empresa(movimientoDTO.getId_empresa());
            movimiento1.setBodega_destino_codigo(movimientoDTO.getBodega_destino_codigo());
            movimiento1.setBodega_origen_codigo(movimientoDTO.getBodega_origen_codigo());
            movimiento1.setFecha_creacion(movimientoDTO.getFecha_creacion());
            movimiento1.setFecha_entrega(movimientoDTO.getFecha_creacion());
            movimientoRepository.updateMovimiento(movimiento1,id);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de Actualizar el moviimiento"+e.getMessage());
        }

    }*/

    public void crearMovimiento(MovimientoDTO movimientoDTO) throws Exception{
        try {
            Movimiento movimiento1 = new Movimiento();
            movimiento1.setId(movimientoDTO.getId());
            movimiento1.setDescripcion(movimientoDTO.getDescripcion());
            movimiento1.setEstado(movimientoDTO.getEstado());
            movimiento1.setId_empresa(movimientoDTO.getId_empresa());
            movimiento1.setBodega_destino_codigo(movimientoDTO.getBodega_destino_codigo());
            movimiento1.setBodega_origen_codigo(movimientoDTO.getBodega_origen_codigo());
            movimiento1.setFecha_creacion(movimientoDTO.getFecha_creacion());
            movimiento1.setFecha_entrega(movimientoDTO.getFecha_creacion());
            entityManager.persist(movimiento1);
            System.out.println("Registro insertado con éxito");
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de crear nuevo registro en la tabla moviimiento"+e.getMessage());
        }
    }


    /*public void eliminarById(Long id) throws Exception{
        try {
            movimientoRepository.deleteMovimiento(id);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de buscar todos los movimientos"+e.getMessage());
        }
    }*/


    public List<Object[]> BuscarEstadoXMovimientoXMovimiento(String estado){
        List<Object[]> movimientoObject =new ArrayList<>();
        try {
            movimientoObject = movimientoRepository.findByEstadoMovimientoXDetalles(estado);
        }catch (Exception e){
            throw new IllegalStateException("Hubo un error al momento de buscar el registro por id en la tabla movimientos"+e.getMessage());
        }
        return movimientoObject;
    }


}
