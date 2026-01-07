package ec.edu.espe.inventoryservice.service;

import ec.edu.espe.inventoryservice.dto.InventarioDTO;
import ec.edu.espe.inventoryservice.exception.ResourceNotFoundException;
import ec.edu.espe.inventoryservice.model.Inventario;
import ec.edu.espe.inventoryservice.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventarioService {
    
    @Autowired
    private InventarioRepository inventarioRepository;
    
    public List<InventarioDTO> getAllInventarios() {
        return inventarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public InventarioDTO getInventarioById(Long id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con id: " + id));
        return convertToDTO(inventario);
    }
    
    public List<InventarioDTO> getInventariosBySucursalId(Long sucursalId) {
        return inventarioRepository.findBySucursalId(sucursalId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<InventarioDTO> getInventariosByMedicamentoId(Long medicamentoId) {
        return inventarioRepository.findByMedicamentoId(medicamentoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<InventarioDTO> getStockBajoBySucursalId(Long sucursalId) {
        return inventarioRepository.findStockBajoBySucursalId(sucursalId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<InventarioDTO> getAllStockBajo() {
        return inventarioRepository.findAllStockBajo().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public InventarioDTO createInventario(InventarioDTO inventarioDTO) {
        // Verificar si ya existe un inventario para esta sucursal y medicamento
        Optional<Inventario> existing = inventarioRepository.findBySucursalIdAndMedicamentoId(
                inventarioDTO.getSucursalId(), inventarioDTO.getMedicamentoId());
        
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Ya existe un inventario para esta sucursal y medicamento");
        }
        
        Inventario inventario = convertToEntity(inventarioDTO);
        Inventario savedInventario = inventarioRepository.save(inventario);
        return convertToDTO(savedInventario);
    }
    
    public InventarioDTO updateInventario(Long id, InventarioDTO inventarioDTO) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con id: " + id));
        
        inventario.setSucursalId(inventarioDTO.getSucursalId());
        inventario.setMedicamentoId(inventarioDTO.getMedicamentoId());
        inventario.setCantidad(inventarioDTO.getCantidad());
        inventario.setStockMinimo(inventarioDTO.getStockMinimo());
        
        Inventario updatedInventario = inventarioRepository.save(inventario);
        return convertToDTO(updatedInventario);
    }
    
    public InventarioDTO actualizarCantidad(Long id, Integer cantidad) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con id: " + id));
        
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        
        inventario.setCantidad(cantidad);
        Inventario updatedInventario = inventarioRepository.save(inventario);
        return convertToDTO(updatedInventario);
    }
    
    public InventarioDTO reducirStock(Long id, Integer cantidadAReducir) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con id: " + id));
        
        if (cantidadAReducir < 0) {
            throw new IllegalArgumentException("La cantidad a reducir no puede ser negativa");
        }
        
        int nuevaCantidad = inventario.getCantidad() - cantidadAReducir;
        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException("No hay suficiente stock. Stock disponible: " + inventario.getCantidad());
        }
        
        inventario.setCantidad(nuevaCantidad);
        Inventario updatedInventario = inventarioRepository.save(inventario);
        return convertToDTO(updatedInventario);
    }
    
    public void deleteInventario(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Inventario no encontrado con id: " + id);
        }
        inventarioRepository.deleteById(id);
    }
    
    private InventarioDTO convertToDTO(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());
        dto.setSucursalId(inventario.getSucursalId());
        dto.setMedicamentoId(inventario.getMedicamentoId());
        dto.setCantidad(inventario.getCantidad());
        dto.setStockMinimo(inventario.getStockMinimo());
        dto.setUltimaActualizacion(inventario.getUltimaActualizacion());
        dto.setStockBajo(inventario.isStockBajo());
        return dto;
    }
    
    private Inventario convertToEntity(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setSucursalId(dto.getSucursalId());
        inventario.setMedicamentoId(dto.getMedicamentoId());
        inventario.setCantidad(dto.getCantidad());
        inventario.setStockMinimo(dto.getStockMinimo());
        return inventario;
    }
}






