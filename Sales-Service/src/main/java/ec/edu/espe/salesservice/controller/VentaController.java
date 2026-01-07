package ec.edu.espe.salesservice.controller;

import ec.edu.espe.salesservice.dto.VentaDTO;
import ec.edu.espe.salesservice.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {
    
    @Autowired
    private VentaService ventaService;
    
    @GetMapping
    public ResponseEntity<List<VentaDTO>> getAllVentas() {
        List<VentaDTO> ventas = ventaService.getAllVentas();
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> getVentaById(@PathVariable Long id) {
        VentaDTO venta = ventaService.getVentaById(id);
        return ResponseEntity.ok(venta);
    }
    
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<VentaDTO>> getVentasBySucursalId(@PathVariable Long sucursalId) {
        List<VentaDTO> ventas = ventaService.getVentasBySucursalId(sucursalId);
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VentaDTO>> getVentasByClienteId(@PathVariable Long clienteId) {
        List<VentaDTO> ventas = ventaService.getVentasByClienteId(clienteId);
        return ResponseEntity.ok(ventas);
    }
    
    @PostMapping
    public ResponseEntity<VentaDTO> createVenta(@Valid @RequestBody VentaDTO ventaDTO) {
        VentaDTO createdVenta = ventaService.createVenta(ventaDTO);
        return new ResponseEntity<>(createdVenta, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}






