package testswisslub.testswisslub.entitys;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private Long id;


    @Column(name="id_empresa",nullable = false)
    private Long id_empresa;

    @Column(name="descripcion",length = 255)
    private String descripcion;

    @Column(name="bodega_origen_codigo",length = 20)
    private String bodega_origen_codigo;

    @Column(name="bodega_destino_codigo",length = 20)
    private String bodega_destino_codigo;

    @Column(name="fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fecha_creacion;

    @Column(name="fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fecha_entrega;

    @Column(name = "estado", columnDefinition = "VARCHAR2(1) DEFAULT 'P'",length = 1)
    private String estado;

   /* @OneToMany(mappedBy = "movimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MovimientosDetalles> movimientoDetalles;*/


    public Movimiento(){}

    public Movimiento(Long id,Long id_empresa,String descripcion , String bodega_origen_codigo ,
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

    /*public List<MovimientosDetalles> getMovimientoDetalles() {
        return movimientoDetalles;
    }

    public void setMovimientoDetalles(List<MovimientosDetalles> movimientoDetalles) {
        this.movimientoDetalles = movimientoDetalles;
    }*/
}
