package ec.edu.espe.reportsservice.service;

import ec.edu.espe.reportsservice.dto.ItemStockBajoDTO;
import ec.edu.espe.reportsservice.dto.ReporteInventarioDTO;
import ec.edu.espe.reportsservice.dto.ReporteVentasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReportsService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${services.catalog.url:http://catalog-service:8081}")
    private String catalogServiceUrl;
    
    @Value("${services.inventory.url:http://inventory-service:8083}")
    private String inventoryServiceUrl;
    
    @Value("${services.sales.url:http://sales-service:8084}")
    private String salesServiceUrl;
    
    @Value("${services.customer.url:http://customer-service:8082}")
    private String customerServiceUrl;
    
    public ReporteVentasDTO generarReporteVentas(Long sucursalId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        ReporteVentasDTO reporte = new ReporteVentasDTO();
        reporte.setSucursalId(sucursalId);
        reporte.setFechaInicio(fechaInicio);
        reporte.setFechaFin(fechaFin);
        
        try {
            // Obtener sucursal
            Map<String, Object> sucursal = restTemplate.getForObject(
                    inventoryServiceUrl + "/api/sucursales/" + sucursalId, 
                    Map.class);
            if (sucursal != null) {
                reporte.setNombreSucursal((String) sucursal.get("nombre"));
            }
            
            // Obtener ventas
            String url = salesServiceUrl + "/api/ventas/sucursal/" + sucursalId;
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );
            
            List<Map<String, Object>> ventas = response.getBody();
            if (ventas != null) {
                // Filtrar por fecha si se proporcionan
                if (fechaInicio != null && fechaFin != null) {
                    ventas = ventas.stream()
                            .filter(v -> {
                                String fechaStr = (String) v.get("fecha");
                                if (fechaStr != null) {
                                    LocalDateTime fecha = LocalDateTime.parse(fechaStr.replace("Z", ""));
                                    return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
                                }
                                return false;
                            })
                            .toList();
                }
                
                reporte.setTotalVentas((long) ventas.size());
                
                BigDecimal totalIngresos = ventas.stream()
                        .map(v -> {
                            Object totalObj = v.get("total");
                            if (totalObj instanceof Number) {
                                return BigDecimal.valueOf(((Number) totalObj).doubleValue());
                            }
                            return BigDecimal.ZERO;
                        })
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                reporte.setTotalIngresos(totalIngresos);
                
                // Contar clientes únicos
                Set<Long> clientesUnicos = new HashSet<>();
                ventas.forEach(v -> {
                    Object clienteId = v.get("clienteId");
                    if (clienteId instanceof Number) {
                        clientesUnicos.add(((Number) clienteId).longValue());
                    }
                });
                reporte.setTotalClientes((long) clientesUnicos.size());
            }
        } catch (Exception e) {
            // En caso de error, retornar valores por defecto
            reporte.setTotalVentas(0L);
            reporte.setTotalIngresos(BigDecimal.ZERO);
            reporte.setTotalClientes(0L);
        }
        
        return reporte;
    }
    
    public ReporteInventarioDTO generarReporteInventario(Long sucursalId) {
        ReporteInventarioDTO reporte = new ReporteInventarioDTO();
        reporte.setSucursalId(sucursalId);
        
        try {
            // Obtener sucursal
            Map<String, Object> sucursal = restTemplate.getForObject(
                    inventoryServiceUrl + "/api/sucursales/" + sucursalId, 
                    Map.class);
            if (sucursal != null) {
                reporte.setNombreSucursal((String) sucursal.get("nombre"));
            }
            
            // Obtener inventario de la sucursal
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    inventoryServiceUrl + "/api/inventarios/sucursal/" + sucursalId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );
            
            List<Map<String, Object>> inventarios = response.getBody();
            if (inventarios != null) {
                reporte.setTotalProductos((long) inventarios.size());
                
                // Obtener stock bajo
                ResponseEntity<List<Map<String, Object>>> stockBajoResponse = restTemplate.exchange(
                        inventoryServiceUrl + "/api/inventarios/stock-bajo/sucursal/" + sucursalId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Map<String, Object>>>() {}
                );
                
                List<Map<String, Object>> stockBajo = stockBajoResponse.getBody();
                if (stockBajo != null) {
                    reporte.setProductosStockBajo((long) stockBajo.size());
                    
                    // Convertir a DTOs
                    List<ItemStockBajoDTO> items = new ArrayList<>();
                    for (Map<String, Object> item : stockBajo) {
                        ItemStockBajoDTO itemDTO = new ItemStockBajoDTO();
                        Object medicamentoId = item.get("medicamentoId");
                        if (medicamentoId instanceof Number) {
                            itemDTO.setMedicamentoId(((Number) medicamentoId).longValue());
                            
                            // Obtener nombre del medicamento
                            try {
                                Map<String, Object> medicamento = restTemplate.getForObject(
                                        catalogServiceUrl + "/api/medicamentos/" + medicamentoId,
                                        Map.class);
                                if (medicamento != null) {
                                    itemDTO.setNombreMedicamento((String) medicamento.get("nombre"));
                                }
                            } catch (Exception e) {
                                itemDTO.setNombreMedicamento("N/A");
                            }
                        }
                        
                        Object cantidad = item.get("cantidad");
                        if (cantidad instanceof Number) {
                            itemDTO.setCantidad(((Number) cantidad).intValue());
                        }
                        
                        Object stockMinimo = item.get("stockMinimo");
                        if (stockMinimo instanceof Number) {
                            itemDTO.setStockMinimo(((Number) stockMinimo).intValue());
                        }
                        
                        items.add(itemDTO);
                    }
                    reporte.setItemsStockBajo(items);
                } else {
                    reporte.setProductosStockBajo(0L);
                    reporte.setItemsStockBajo(new ArrayList<>());
                }
            } else {
                reporte.setTotalProductos(0L);
                reporte.setProductosStockBajo(0L);
                reporte.setItemsStockBajo(new ArrayList<>());
            }
        } catch (Exception e) {
            reporte.setTotalProductos(0L);
            reporte.setProductosStockBajo(0L);
            reporte.setItemsStockBajo(new ArrayList<>());
        }
        
        return reporte;
    }
}






