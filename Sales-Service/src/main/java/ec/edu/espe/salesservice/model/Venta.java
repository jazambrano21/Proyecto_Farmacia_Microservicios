package ec.edu.espe.salesservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "El ID de la sucursal es obligatorio")
    @Column(name = "sucursal_id", nullable = false)
    private Long sucursalId;
    
    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
    
    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDateTime fecha;
    
    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    
    @Size(max = 200, message = "Las observaciones no pueden exceder 200 caracteres")
    @Column(length = 200)
    private String observaciones;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;
    
    // Constructores
    public Venta() {
        this.fecha = LocalDateTime.now();
    }
    
    public Venta(Long sucursalId, Long clienteId, BigDecimal total) {
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
    
    public List<DetalleVenta> getDetalles() {
        return detalles;
    }
    
    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}


