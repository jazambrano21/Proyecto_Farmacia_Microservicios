package ec.edu.espe.reportsservice.controller;

import ec.edu.espe.reportsservice.dto.ReporteInventarioDTO;
import ec.edu.espe.reportsservice.dto.ReporteVentasDTO;
import ec.edu.espe.reportsservice.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReportsController {
    
    @Autowired
    private ReportsService reportsService;
    
    @GetMapping("/ventas/sucursal/{sucursalId}")
    public ResponseEntity<ReporteVentasDTO> getReporteVentas(
            @PathVariable Long sucursalId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        
        ReporteVentasDTO reporte = reportsService.generarReporteVentas(sucursalId, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }
    
    @GetMapping("/inventario/sucursal/{sucursalId}")
    public ResponseEntity<ReporteInventarioDTO> getReporteInventario(@PathVariable Long sucursalId) {
        ReporteInventarioDTO reporte = reportsService.generarReporteInventario(sucursalId);
        return ResponseEntity.ok(reporte);
    }
}






