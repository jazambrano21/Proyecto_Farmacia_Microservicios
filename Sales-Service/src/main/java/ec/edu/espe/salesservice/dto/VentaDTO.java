package ec.edu.espe.salesservice.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VentaDTO {
    
    private Long id;
    
    @NotNull(message = "El ID de la sucursal es obligatorio")
    private Long sucursalId;
    
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;
    
    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fecha;
    
    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    private BigDecimal total;
    
    @Size(max = 200, message = "Las observaciones no pueden exceder 200 caracteres")
    private String observaciones;
    
    private List<DetalleVentaDTO> detalles;
    
    // Constructores
    public VentaDTO() {
    }
    
    public VentaDTO(Long sucursalId, Long clienteId, BigDecimal total) {
        this.sucursalId = sucursalId;
        this.clienteId = clienteId;
        this.total = total;
        this.fecha = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getSucursalId() {
        return sucursalId;
    }
    
    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }
    
    public Long getClienteId() {
        return clienteId;
    }
    
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public List<DetalleVentaDTO> getDetalles() {
        return detalles;
    }
    
    public void setDetalles(List<DetalleVentaDTO> detalles) {
        this.detalles = detalles;
    }
}






