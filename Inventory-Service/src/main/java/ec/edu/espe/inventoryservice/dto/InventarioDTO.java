package ec.edu.espe.inventoryservice.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class InventarioDTO {
    
    private Long id;
    
    @NotNull(message = "El ID de la sucursal es obligatorio")
    private Long sucursalId;
    
    @NotNull(message = "El ID del medicamento es obligatorio")
    private Long medicamentoId;
    
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidad;
    
    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer stockMinimo;
    
    private LocalDateTime ultimaActualizacion;
    
    private Boolean stockBajo;
    
    // Constructores
    public InventarioDTO() {
    }
    
    public InventarioDTO(Long sucursalId, Long medicamentoId, Integer cantidad, Integer stockMinimo) {
        this.sucursalId = sucursalId;
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
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
    
    public Long getMedicamentoId() {
        return medicamentoId;
    }
    
    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    public Integer getStockMinimo() {
        return stockMinimo;
    }
    
    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    
    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }
    
    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
    
    public Boolean getStockBajo() {
        return stockBajo;
    }
    
    public void setStockBajo(Boolean stockBajo) {
        this.stockBajo = stockBajo;
    }
}


