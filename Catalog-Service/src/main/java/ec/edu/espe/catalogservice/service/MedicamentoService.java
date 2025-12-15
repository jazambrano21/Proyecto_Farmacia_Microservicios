package ec.edu.espe.catalogservice.service;

import ec.edu.espe.catalogservice.dto.MedicamentoDTO;
import ec.edu.espe.catalogservice.exception.ResourceNotFoundException;
import ec.edu.espe.catalogservice.model.Medicamento;
import ec.edu.espe.catalogservice.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicamentoService {
    
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    
    public List<MedicamentoDTO> getAllMedicamentos() {
        return medicamentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public MedicamentoDTO getMedicamentoById(Long id) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento no encontrado con id: " + id));
        return convertToDTO(medicamento);
    }
    
    public MedicamentoDTO createMedicamento(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = convertToEntity(medicamentoDTO);
        Medicamento savedMedicamento = medicamentoRepository.save(medicamento);
        return convertToDTO(savedMedicamento);
    }
    
    public MedicamentoDTO updateMedicamento(Long id, MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento no encontrado con id: " + id));
        
        medicamento.setNombre(medicamentoDTO.getNombre());
        medicamento.setDescripcion(medicamentoDTO.getDescripcion());
        medicamento.setPrecio(medicamentoDTO.getPrecio());
        medicamento.setCategoria(medicamentoDTO.getCategoria());
        medicamento.setRequierePrescripcion(medicamentoDTO.getRequierePrescripcion());
        medicamento.setLaboratorio(medicamentoDTO.getLaboratorio());
        medicamento.setCodigoBarras(medicamentoDTO.getCodigoBarras());
        
        Medicamento updatedMedicamento = medicamentoRepository.save(medicamento);
        return convertToDTO(updatedMedicamento);
    }
    
    public void deleteMedicamento(Long id) {
        if (!medicamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medicamento no encontrado con id: " + id);
        }
        medicamentoRepository.deleteById(id);
    }
    
    public List<MedicamentoDTO> getMedicamentosByCategoria(String categoria) {
        return medicamentoRepository.findByCategoria(categoria).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<MedicamentoDTO> getMedicamentosByPrescripcion(Boolean requierePrescripcion) {
        return medicamentoRepository.findByRequierePrescripcion(requierePrescripcion).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<MedicamentoDTO> searchMedicamentosByNombre(String nombre) {
        return medicamentoRepository.findByNombreContaining(nombre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private MedicamentoDTO convertToDTO(Medicamento medicamento) {
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(medicamento.getId());
        dto.setNombre(medicamento.getNombre());
        dto.setDescripcion(medicamento.getDescripcion());
        dto.setPrecio(medicamento.getPrecio());
        dto.setCategoria(medicamento.getCategoria());
        dto.setRequierePrescripcion(medicamento.getRequierePrescripcion());
        dto.setLaboratorio(medicamento.getLaboratorio());
        dto.setCodigoBarras(medicamento.getCodigoBarras());
        return dto;
    }
    
    private Medicamento convertToEntity(MedicamentoDTO dto) {
        Medicamento medicamento = new Medicamento();
        medicamento.setNombre(dto.getNombre());
        medicamento.setDescripcion(dto.getDescripcion());
        medicamento.setPrecio(dto.getPrecio());
        medicamento.setCategoria(dto.getCategoria());
        medicamento.setRequierePrescripcion(dto.getRequierePrescripcion());
        medicamento.setLaboratorio(dto.getLaboratorio());
        medicamento.setCodigoBarras(dto.getCodigoBarras());
        return medicamento;
    }
}


