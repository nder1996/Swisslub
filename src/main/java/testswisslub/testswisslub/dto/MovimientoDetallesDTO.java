package testswisslub.testswisslub.dto;

import jakarta.persistence.*;
import testswisslub.testswisslub.entitys.Movimiento;

public class MovimientoDetallesDTO {


    private Long id;



    private Long movimiento_id;



    private Movimiento movimiento;


    private String item_codigo;



    private Long cantidad_enviada;


    public MovimientoDetallesDTO(){}

    public MovimientoDetallesDTO(Long id,Movimiento movimiento,String item_codigo , Long cantidad_enviada){
        this.id=id;
        this.movimiento=movimiento;
        this.item_codigo=item_codigo;
        this.cantidad_enviada=cantidad_enviada;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public String getItem_codigo() {
        return item_codigo;
    }

    public void setItem_codigo(String item_codigo) {
        this.item_codigo = item_codigo;
    }

    public Long getCantidad_enviada() {
        return cantidad_enviada;
    }

    public void setCantidad_enviada(Long cantidad_enviada) {
        this.cantidad_enviada = cantidad_enviada;
    }
}
