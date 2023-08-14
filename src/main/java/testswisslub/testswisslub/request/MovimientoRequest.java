package testswisslub.testswisslub.request;

import testswisslub.testswisslub.entitys.Movimiento;
import testswisslub.testswisslub.entitys.MovimientosDetalles;

import java.util.List;

public class MovimientoRequest {

    private Movimiento movimiento;
    private List<MovimientosDetalles> detalles;


    public MovimientoRequest(Movimiento movimiento,List<MovimientosDetalles> detalles){
    this.movimiento=movimiento;
    this.detalles=detalles;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public List<MovimientosDetalles> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<MovimientosDetalles> detalles) {
        this.detalles = detalles;
    }
}
