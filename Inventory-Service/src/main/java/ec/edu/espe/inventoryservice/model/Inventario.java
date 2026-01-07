package ec.edu.espe.inventoryservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventarios", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"sucursal_id", "medicamento_id"}))
public class Inventario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "El ID de la sucursal es obligatorio")
    @Column(name = "sucursal_id", nullable = false)
    private Long sucursalId;
    
    @NotNull(message = "El ID del medicamento es obligatorio")
    @Column(name = "medicamento_id", nullable = false)
    private Long medicamentoId;
    
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @Column(nullable = false)
    private Integer cantidad;
    
    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo;
    
    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
    
    // Constructores
    public Inventario() {
        this.ultimaActualizacion = LocalDateTime.now();
    }
    
    public Inventario(Long sucursalId, Long medicamentoId, Integer cantidad, Integer stockMinimo) {
        this.sucursalId = sucursalId;
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
        this.ultimaActualizacion = LocalDateTime.now();
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
        this.ultimaActualizacion = LocalDateTime.now();
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
    
    public Boolean isStockBajo() {
        return cantidad <= stockMinimo;
    }
}






