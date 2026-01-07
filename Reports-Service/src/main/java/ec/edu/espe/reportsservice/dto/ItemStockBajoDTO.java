package ec.edu.espe.reportsservice.dto;

public class ItemStockBajoDTO {
    
    private Long medicamentoId;
    private String nombreMedicamento;
    private Integer cantidad;
    private Integer stockMinimo;
    
    // Constructores
    public ItemStockBajoDTO() {
    }
    
    // Getters y Setters
    public Long getMedicamentoId() {
        return medicamentoId;
    }
    
    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }
    
    public String getNombreMedicamento() {
        return nombreMedicamento;
    }
    
    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
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
}






