package ec.edu.espe.inventoryservice.controller;

import ec.edu.espe.inventoryservice.dto.SucursalDTO;
import ec.edu.espe.inventoryservice.service.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins = "*")
public class SucursalController {
    
    @Autowired
    private SucursalService sucursalService;
    
    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getAllSucursales() {
        List<SucursalDTO> sucursales = sucursalService.getAllSucursales();
        return ResponseEntity.ok(sucursales);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> getSucursalById(@PathVariable Long id) {
        SucursalDTO sucursal = sucursalService.getSucursalById(id);
        return ResponseEntity.ok(sucursal);
    }
    
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<SucursalDTO>> getSucursalesByCiudad(@PathVariable String ciudad) {
        List<SucursalDTO> sucursales = sucursalService.getSucursalesByCiudad(ciudad);
        return ResponseEntity.ok(sucursales);
    }
    
    @PostMapping
    public ResponseEntity<SucursalDTO> createSucursal(@Valid @RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO createdSucursal = sucursalService.createSucursal(sucursalDTO);
        return new ResponseEntity<>(createdSucursal, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> updateSucursal(
            @PathVariable Long id, 
            @Valid @RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO updatedSucursal = sucursalService.updateSucursal(id, sucursalDTO);
        return ResponseEntity.ok(updatedSucursal);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }
}






