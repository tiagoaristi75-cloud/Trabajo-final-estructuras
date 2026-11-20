import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/// HU4: contrato de renting.
/// IdContrato, CedulaCliente y PlacaVehiculo son final: no se pueden modificar
/// una vez registrados. Solo cambian las fechas o la duracion.
/// Se agrega el campo "activo" para poder agrupar activos y finalizados (HU7).

public class ContratoRenting {
    private final String idContrato;
    private final String cedulaCliente;
    private final String placaVehiculo;
    private String fechaInicio;
    private String fechaFin;
    private int totalDias;
    private float valorTotal;
    private boolean activo = true;

    public ContratoRenting(String idContrato, String cedulaCliente, String placaVehiculo,
            String fechaInicio, String fechaFin, float valorDia) {
        this.idContrato = idContrato;
        this.cedulaCliente = cedulaCliente;
        this.placaVehiculo = placaVehiculo;
        cambiarFechas(fechaInicio, fechaFin, valorDia);
    }

    /// Cambia fechas y recalcula dias y valor total.
    
    public void cambiarFechas(String inicio, String fin, float valorDia) {
        this.fechaInicio = inicio;
        this.fechaFin = fin;
        this.totalDias = (int) ChronoUnit.DAYS.between(LocalDate.parse(inicio), LocalDate.parse(fin));
        this.valorTotal = totalDias * valorDia;
    }

    public String getIdContrato() {
        return idContrato;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void finalizar() {
        this.activo = false;
    }
}