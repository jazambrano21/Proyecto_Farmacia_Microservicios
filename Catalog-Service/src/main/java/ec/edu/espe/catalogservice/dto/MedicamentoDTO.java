package ec.edu.espe.catalogservice.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class MedicamentoDTO {
    
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;
    
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal precio;
    
    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    private String categoria;
    
    @NotNull(message = "El campo requierePrescripcion es obligatorio")
    private Boolean requierePrescripcion;
    
    private String laboratorio;
    
    private String codigoBarras;
    
    // Constructores
    public MedicamentoDTO() {
    }
    
    public MedicamentoDTO(Long id, String nombre, String descripcion, BigDecimal precio, 
                         String categoria, Boolean requierePrescripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.requierePrescripcion = requierePrescripcion;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public BigDecimal getPrecio() {
        return precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public Boolean getRequierePrescripcion() {
        return requierePrescripcion;
    }
    
    public void setRequierePrescripcion(Boolean requierePrescripcion) {
        this.requierePrescripcion = requierePrescripcion;
    }
    
    public String getLaboratorio() {
        return laboratorio;
    }
    
    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }
    
    public String getCodigoBarras() {
        return codigoBarras;
    }
    
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}






