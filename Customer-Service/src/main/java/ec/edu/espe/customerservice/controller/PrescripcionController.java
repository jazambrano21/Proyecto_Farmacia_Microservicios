package ec.edu.espe.customerservice.controller;

import ec.edu.espe.customerservice.dto.PrescripcionDTO;
import ec.edu.espe.customerservice.service.PrescripcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescripciones")
@CrossOrigin(origins = "*")
public class PrescripcionController {
    
    @Autowired
    private PrescripcionService prescripcionService;
    
    @GetMapping
    public ResponseEntity<List<PrescripcionDTO>> getAllPrescripciones() {
        List<PrescripcionDTO> prescripciones = prescripcionService.getAllPrescripciones();
        return ResponseEntity.ok(prescripciones);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PrescripcionDTO> getPrescripcionById(@PathVariable Long id) {
        PrescripcionDTO prescripcion = prescripcionService.getPrescripcionById(id);
        return ResponseEntity.ok(prescripcion);
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PrescripcionDTO>> getPrescripcionesByClienteId(@PathVariable Long clienteId) {
        List<PrescripcionDTO> prescripciones = prescripcionService.getPrescripcionesByClienteId(clienteId);
        return ResponseEntity.ok(prescripciones);
    }
    
    @GetMapping("/cliente/{clienteId}/activas")
    public ResponseEntity<List<PrescripcionDTO>> getPrescripcionesActivasByClienteId(@PathVariable Long clienteId) {
        List<PrescripcionDTO> prescripciones = prescripcionService.getPrescripcionesActivasByClienteId(clienteId);
        return ResponseEntity.ok(prescripciones);
    }
    
    @PostMapping
    public ResponseEntity<PrescripcionDTO> createPrescripcion(@Valid @RequestBody PrescripcionDTO prescripcionDTO) {
        PrescripcionDTO createdPrescripcion = prescripcionService.createPrescripcion(prescripcionDTO);
        return new ResponseEntity<>(createdPrescripcion, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PrescripcionDTO> updatePrescripcion(
            @PathVariable Long id, 
            @Valid @RequestBody PrescripcionDTO prescripcionDTO) {
        PrescripcionDTO updatedPrescripcion = prescripcionService.updatePrescripcion(id, prescripcionDTO);
        return ResponseEntity.ok(updatedPrescripcion);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescripcion(@PathVariable Long id) {
        prescripcionService.deletePrescripcion(id);
        return ResponseEntity.noContent().build();
    }
}






