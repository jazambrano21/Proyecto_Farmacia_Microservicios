package ec.edu.espe.customerservice.service;

import ec.edu.espe.customerservice.dto.PrescripcionDTO;
import ec.edu.espe.customerservice.exception.ResourceNotFoundException;
import ec.edu.espe.customerservice.model.Prescripcion;
import ec.edu.espe.customerservice.repository.PrescripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrescripcionService {
    
    @Autowired
    private PrescripcionRepository prescripcionRepository;
    
    public List<PrescripcionDTO> getAllPrescripciones() {
        return prescripcionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public PrescripcionDTO getPrescripcionById(Long id) {
        Prescripcion prescripcion = prescripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescripción no encontrada con id: " + id));
        return convertToDTO(prescripcion);
    }
    
    public List<PrescripcionDTO> getPrescripcionesByClienteId(Long clienteId) {
        return prescripcionRepository.findByClienteId(clienteId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<PrescripcionDTO> getPrescripcionesActivasByClienteId(Long clienteId) {
        return prescripcionRepository.findByClienteIdAndActiva(clienteId, true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public PrescripcionDTO createPrescripcion(PrescripcionDTO prescripcionDTO) {
        Prescripcion prescripcion = convertToEntity(prescripcionDTO);
        Prescripcion savedPrescripcion = prescripcionRepository.save(prescripcion);
        return convertToDTO(savedPrescripcion);
    }
    
    public PrescripcionDTO updatePrescripcion(Long id, PrescripcionDTO prescripcionDTO) {
        Prescripcion prescripcion = prescripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescripción no encontrada con id: " + id));
        
        prescripcion.setClienteId(prescripcionDTO.getClienteId());
        prescripcion.setMedicamentoId(prescripcionDTO.getMedicamentoId());
        prescripcion.setFecha(prescripcionDTO.getFecha());
        prescripcion.setMedico(prescripcionDTO.getMedico());
        prescripcion.setObservaciones(prescripcionDTO.getObservaciones());
        prescripcion.setActiva(prescripcionDTO.getActiva());
        
        Prescripcion updatedPrescripcion = prescripcionRepository.save(prescripcion);
        return convertToDTO(updatedPrescripcion);
    }
    
    public void deletePrescripcion(Long id) {
        if (!prescripcionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prescripción no encontrada con id: " + id);
        }
        prescripcionRepository.deleteById(id);
    }
    
    private PrescripcionDTO convertToDTO(Prescripcion prescripcion) {
        PrescripcionDTO dto = new PrescripcionDTO();
        dto.setId(prescripcion.getId());
        dto.setClienteId(prescripcion.getClienteId());
        dto.setMedicamentoId(prescripcion.getMedicamentoId());
        dto.setFecha(prescripcion.getFecha());
        dto.setMedico(prescripcion.getMedico());
        dto.setObservaciones(prescripcion.getObservaciones());
        dto.setActiva(prescripcion.getActiva());
        return dto;
    }
    
    private Prescripcion convertToEntity(PrescripcionDTO dto) {
        Prescripcion prescripcion = new Prescripcion();
        prescripcion.setClienteId(dto.getClienteId());
        prescripcion.setMedicamentoId(dto.getMedicamentoId());
        prescripcion.setFecha(dto.getFecha());
        prescripcion.setMedico(dto.getMedico());
        prescripcion.setObservaciones(dto.getObservaciones());
        prescripcion.setActiva(dto.getActiva());
        return prescripcion;
    }
}


