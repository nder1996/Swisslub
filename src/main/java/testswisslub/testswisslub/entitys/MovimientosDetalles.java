package testswisslub.testswisslub.entitys;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "movimiento_detalle")
public class MovimientosDetalles {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id" , nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "movimiento_id", referencedColumnName = "id", nullable = false)
    private Movimiento movimiento;




    @Column(name = "item_codigo", length = 20)
    private String item_codigo;


    @Column(name="cantidad_enviada",nullable = false)
    private Long cantidad_enviada;


    public MovimientosDetalles(){}

    public MovimientosDetalles(Long id,Movimiento movimiento,String item_codigo , Long cantidad_enviada){
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
