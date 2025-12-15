package ec.edu.espe.reportsservice.dto;

import java.util.List;

public class ReporteInventarioDTO {
    
    private Long sucursalId;
    private String nombreSucursal;
    private Long totalProductos;
    private Long productosStockBajo;
    private List<ItemStockBajoDTO> itemsStockBajo;
    
    // Constructores
    public ReporteInventarioDTO() {
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
    
    public Long getTotalProductos() {
        return totalProductos;
    }
    
    public void setTotalProductos(Long totalProductos) {
        this.totalProductos = totalProductos;
    }
    
    public Long getProductosStockBajo() {
        return productosStockBajo;
    }
    
    public void setProductosStockBajo(Long productosStockBajo) {
        this.productosStockBajo = productosStockBajo;
    }
    
    public List<ItemStockBajoDTO> getItemsStockBajo() {
        return itemsStockBajo;
    }
    
    public void setItemsStockBajo(List<ItemStockBajoDTO> itemsStockBajo) {
        this.itemsStockBajo = itemsStockBajo;
    }
}


