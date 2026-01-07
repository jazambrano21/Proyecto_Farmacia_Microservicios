package ec.edu.espe.reportsservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReporteVentasDTO {
    
    private Long sucursalId;
    private String nombreSucursal;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Long totalVentas;
    private BigDecimal totalIngresos;
    private Long totalClientes;
    
    // Constructores
    public ReporteVentasDTO() {
    }
    
    // Getters y Setters
    public Long getSucursalId() {
        return sucursalId;
    }
    
    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }
    
    public String getNombreSucursal() {
        return nombreSucursal;
    }
    
    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
    
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public Long getTotalVentas() {
        return totalVentas;
    }
    
    public void setTotalVentas(Long totalVentas) {
        this.totalVentas = totalVentas;
    }
    
    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }
    
    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }
    
    public Long getTotalClientes() {
        return totalClientes;
    }
    
    public void setTotalClientes(Long totalClientes) {
        this.totalClientes = totalClientes;
    }
}






