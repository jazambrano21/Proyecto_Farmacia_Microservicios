package ec.edu.espe.inventoryservice.service;

import ec.edu.espe.inventoryservice.dto.SucursalDTO;
import ec.edu.espe.inventoryservice.exception.ResourceNotFoundException;
import ec.edu.espe.inventoryservice.model.Sucursal;
import ec.edu.espe.inventoryservice.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SucursalService {
    
    @Autowired
    private SucursalRepository sucursalRepository;
    
    public List<SucursalDTO> getAllSucursales() {
        return sucursalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public SucursalDTO getSucursalById(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada con id: " + id));
        return convertToDTO(sucursal);
    }
    
    public List<SucursalDTO> getSucursalesByCiudad(String ciudad) {
        return sucursalRepository.findByCiudad(ciudad).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public SucursalDTO createSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = convertToEntity(sucursalDTO);
        Sucursal savedSucursal = sucursalRepository.save(sucursal);
        return convertToDTO(savedSucursal);
    }
    
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDTO) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada con id: " + id));
        
        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setCiudad(sucursalDTO.getCiudad());
        sucursal.setDireccion(sucursalDTO.getDireccion());
        sucursal.setTelefono(sucursalDTO.getTelefono());
        
        Sucursal updatedSucursal = sucursalRepository.save(sucursal);
        return convertToDTO(updatedSucursal);
    }
    
    public void deleteSucursal(Long id) {
        if (!sucursalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sucursal no encontrada con id: " + id);
        }
        sucursalRepository.deleteById(id);
    }
    
    private SucursalDTO convertToDTO(Sucursal sucursal) {
        SucursalDTO dto = new SucursalDTO();
        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setCiudad(sucursal.getCiudad());
        dto.setDireccion(sucursal.getDireccion());
        dto.setTelefono(sucursal.getTelefono());
        dto.setFechaRegistro(sucursal.getFechaRegistro());
        return dto;
    }
    
    private Sucursal convertToEntity(SucursalDTO dto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(dto.getNombre());
        sucursal.setCiudad(dto.getCiudad());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setTelefono(dto.getTelefono());
        return sucursal;
    }
}


