package ec.edu.espe.inventoryservice.controller;

import ec.edu.espe.inventoryservice.dto.InventarioDTO;
import ec.edu.espe.inventoryservice.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
@CrossOrigin(origins = "*")
public class InventarioController {
    
    @Autowired
    private InventarioService inventarioService;
    
    @GetMapping
    public ResponseEntity<List<InventarioDTO>> getAllInventarios() {
        List<InventarioDTO> inventarios = inventarioService.getAllInventarios();
        return ResponseEntity.ok(inventarios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> getInventarioById(@PathVariable Long id) {
        InventarioDTO inventario = inventarioService.getInventarioById(id);
        return ResponseEntity.ok(inventario);
    }
    
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<InventarioDTO>> getInventariosBySucursalId(@PathVariable Long sucursalId) {
        List<InventarioDTO> inventarios = inventarioService.getInventariosBySucursalId(sucursalId);
        return ResponseEntity.ok(inventarios);
    }
    
    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<List<InventarioDTO>> getInventariosByMedicamentoId(@PathVariable Long medicamentoId) {
        List<InventarioDTO> inventarios = inventarioService.getInventariosByMedicamentoId(medicamentoId);
        return ResponseEntity.ok(inventarios);
    }
    
    @GetMapping("/stock-bajo/sucursal/{sucursalId}")
    public ResponseEntity<List<InventarioDTO>> getStockBajoBySucursalId(@PathVariable Long sucursalId) {
        List<InventarioDTO> inventarios = inventarioService.getStockBajoBySucursalId(sucursalId);
        return ResponseEntity.ok(inventarios);
    }
    
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<InventarioDTO>> getAllStockBajo() {
        List<InventarioDTO> inventarios = inventarioService.getAllStockBajo();
        return ResponseEntity.ok(inventarios);
    }
    
    @PostMapping
    public ResponseEntity<InventarioDTO> createInventario(@Valid @RequestBody InventarioDTO inventarioDTO) {
        InventarioDTO createdInventario = inventarioService.createInventario(inventarioDTO);
        return new ResponseEntity<>(createdInventario, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> updateInventario(
            @PathVariable Long id, 
            @Valid @RequestBody InventarioDTO inventarioDTO) {
        InventarioDTO updatedInventario = inventarioService.updateInventario(id, inventarioDTO);
        return ResponseEntity.ok(updatedInventario);
    }
    
    @PatchMapping("/{id}/cantidad")
    public ResponseEntity<InventarioDTO> actualizarCantidad(
            @PathVariable Long id, 
            @RequestParam Integer cantidad) {
        InventarioDTO updatedInventario = inventarioService.actualizarCantidad(id, cantidad);
        return ResponseEntity.ok(updatedInventario);
    }
    
    @PatchMapping("/{id}/reducir")
    public ResponseEntity<InventarioDTO> reducirStock(
            @PathVariable Long id, 
            @RequestParam Integer cantidad) {
        InventarioDTO updatedInventario = inventarioService.reducirStock(id, cantidad);
        return ResponseEntity.ok(updatedInventario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        inventarioService.deleteInventario(id);
        return ResponseEntity.noContent().build();
    }
}


