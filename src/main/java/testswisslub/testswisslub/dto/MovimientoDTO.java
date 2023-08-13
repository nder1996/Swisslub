package testswisslub.testswisslub.dto;

import jakarta.persistence.*;
import testswisslub.testswisslub.entitys.MovimientosDetalles;

import java.util.Date;
import java.util.List;

public class MovimientoDTO {



    private Long id;


    private Long id_empresa;


    private String descripcion;


    private String bodega_origen_codigo;


    private String bodega_destino_codigo;

    private Date fecha_creacion;


    private Date fecha_entrega;


    private String estado;


    private List<MovimientosDetalles> movimientoDetalles;


    public MovimientoDTO(){}

    public MovimientoDTO(Long id,Long id_empresa,String descripcion , String bodega_origen_codigo ,
                      String bodega_destino_codigo , Date fecha_creacion , Date fecha_entrega , String estado){
        this.id=id;
        this.id_empresa=id_empresa;
        this.descripcion=descripcion;
        this.bodega_origen_codigo=bodega_origen_codigo;
        this.bodega_destino_codigo=bodega_destino_codigo;
        this.fecha_creacion=fecha_creacion;
        this.fecha_entrega=fecha_entrega;
        this.estado=estado;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBodega_origen_codigo() {
        return bodega_origen_codigo;
    }

    public void setBodega_origen_codigo(String bodega_origen_codigo) {
        this.bodega_origen_codigo = bodega_origen_codigo;
    }

    public String getBodega_destino_codigo() {
        return bodega_destino_codigo;
    }

    public void setBodega_destino_codigo(String bodega_destino_codigo) {
        this.bodega_destino_codigo = bodega_destino_codigo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<MovimientosDetalles> getMovimientoDetalles() {
        return movimientoDetalles;
    }

    public void setMovimientoDetalles(List<MovimientosDetalles> movimientoDetalles) {
        this.movimientoDetalles = movimientoDetalles;
    }
}
