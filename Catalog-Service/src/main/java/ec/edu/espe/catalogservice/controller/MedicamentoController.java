package ec.edu.espe.catalogservice.controller;

import ec.edu.espe.catalogservice.dto.MedicamentoDTO;
import ec.edu.espe.catalogservice.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoController {
    
    @Autowired
    private MedicamentoService medicamentoService;
    
    @GetMapping
    public ResponseEntity<List<MedicamentoDTO>> getAllMedicamentos() {
        List<MedicamentoDTO> medicamentos = medicamentoService.getAllMedicamentos();
        return ResponseEntity.ok(medicamentos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> getMedicamentoById(@PathVariable Long id) {
        MedicamentoDTO medicamento = medicamentoService.getMedicamentoById(id);
        return ResponseEntity.ok(medicamento);
    }
    
    @PostMapping
    public ResponseEntity<MedicamentoDTO> createMedicamento(@Valid @RequestBody MedicamentoDTO medicamentoDTO) {
        MedicamentoDTO createdMedicamento = medicamentoService.createMedicamento(medicamentoDTO);
        return new ResponseEntity<>(createdMedicamento, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> updateMedicamento(
            @PathVariable Long id, 
            @Valid @RequestBody MedicamentoDTO medicamentoDTO) {
        MedicamentoDTO updatedMedicamento = medicamentoService.updateMedicamento(id, medicamentoDTO);
        return ResponseEntity.ok(updatedMedicamento);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable Long id) {
        medicamentoService.deleteMedicamento(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<MedicamentoDTO>> getMedicamentosByCategoria(@PathVariable String categoria) {
        List<MedicamentoDTO> medicamentos = medicamentoService.getMedicamentosByCategoria(categoria);
        return ResponseEntity.ok(medicamentos);
    }
    
    @GetMapping("/prescripcion/{requierePrescripcion}")
    public ResponseEntity<List<MedicamentoDTO>> getMedicamentosByPrescripcion(
            @PathVariable Boolean requierePrescripcion) {
        List<MedicamentoDTO> medicamentos = medicamentoService.getMedicamentosByPrescripcion(requierePrescripcion);
        return ResponseEntity.ok(medicamentos);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<MedicamentoDTO>> searchMedicamentos(@RequestParam String nombre) {
        List<MedicamentoDTO> medicamentos = medicamentoService.searchMedicamentosByNombre(nombre);
        return ResponseEntity.ok(medicamentos);
    }
}






