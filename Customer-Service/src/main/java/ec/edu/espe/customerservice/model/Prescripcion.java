package ec.edu.espe.customerservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescripciones")
public class Prescripcion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
    
    @NotNull(message = "El ID del medicamento es obligatorio")
    @Column(name = "medicamento_id", nullable = false)
    private Long medicamentoId;
    
    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDate fecha;
    
    @NotBlank(message = "El nombre del médico es obligatorio")
    @Size(max = 100, message = "El nombre del médico no puede exceder 100 caracteres")
    @Column(name = "medico", nullable = false, length = 100)
    private String medico;
    
    @Size(max = 200, message = "Las observaciones no pueden exceder 200 caracteres")
    @Column(length = 200)
    private String observaciones;
    
    @NotNull(message = "El campo activa es obligatorio")
    @Column(nullable = false)
    private Boolean activa;
    
    // Constructores
    public Prescripcion() {
        this.activa = true;
        this.fecha = LocalDate.now();
    }
    
    public Prescripcion(Long clienteId, Long medicamentoId, LocalDate fecha, String medico) {
        this.clienteId = clienteId;
        this.medicamentoId = medicamentoId;
        this.fecha = fecha;
        this.medico = medico;
        this.activa = true;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getClienteId() {
        return clienteId;
    }
    
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    
    public Long getMedicamentoId() {
        return medicamentoId;
    }
    
    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public String getMedico() {
        return medico;
    }
    
    public void setMedico(String medico) {
        this.medico = medico;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public Boolean getActiva() {
        return activa;
    }
    
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}






