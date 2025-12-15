package ec.edu.espe.salesservice.service;

import ec.edu.espe.salesservice.dto.DetalleVentaDTO;
import ec.edu.espe.salesservice.dto.VentaDTO;
import ec.edu.espe.salesservice.exception.ResourceNotFoundException;
import ec.edu.espe.salesservice.model.DetalleVenta;
import ec.edu.espe.salesservice.model.Venta;
import ec.edu.espe.salesservice.repository.DetalleVentaRepository;
import ec.edu.espe.salesservice.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VentaService {
    
    @Autowired
    private VentaRepository ventaRepository;
    
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    
    public List<VentaDTO> getAllVentas() {
        return ventaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public VentaDTO getVentaById(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + id));
        return convertToDTO(venta);
    }
    
    public List<VentaDTO> getVentasBySucursalId(Long sucursalId) {
        return ventaRepository.findBySucursalId(sucursalId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<VentaDTO> getVentasByClienteId(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public VentaDTO createVenta(VentaDTO ventaDTO) {
        // Calcular el total si no viene calculado
        if (ventaDTO.getTotal() == null && ventaDTO.getDetalles() != null) {
            BigDecimal total = ventaDTO.getDetalles().stream()
                    .map(DetalleVentaDTO::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            ventaDTO.setTotal(total);
        }
        
        Venta venta = convertToEntity(ventaDTO);
        Venta savedVenta = ventaRepository.save(venta);
        
        // Guardar los detalles
        if (ventaDTO.getDetalles() != null && !ventaDTO.getDetalles().isEmpty()) {
            for (DetalleVentaDTO detalleDTO : ventaDTO.getDetalles()) {
                detalleDTO.setVentaId(savedVenta.getId());
                DetalleVenta detalle = convertDetalleToEntity(detalleDTO);
                detalleVentaRepository.save(detalle);
            }
        }
        
        return convertToDTO(savedVenta);
    }
    
    public void deleteVenta(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Venta no encontrada con id: " + id);
        }
        // Los detalles se eliminan en cascada
        ventaRepository.deleteById(id);
    }
    
    private VentaDTO convertToDTO(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setId(venta.getId());
        dto.setSucursalId(venta.getSucursalId());
        dto.setClienteId(venta.getClienteId());
        dto.setFecha(venta.getFecha());
        dto.setTotal(venta.getTotal());
        dto.setObservaciones(venta.getObservaciones());
        
        // Convertir detalles
        if (venta.getDetalles() != null) {
            List<DetalleVentaDTO> detallesDTO = venta.getDetalles().stream()
                    .map(this::convertDetalleToDTO)
                    .collect(Collectors.toList());
            dto.setDetalles(detallesDTO);
        }
        
        return dto;
    }
    
    private Venta convertToEntity(VentaDTO dto) {
        Venta venta = new Venta();
        venta.setSucursalId(dto.getSucursalId());
        venta.setClienteId(dto.getClienteId());
        venta.setFecha(dto.getFecha() != null ? dto.getFecha() : java.time.LocalDateTime.now());
        venta.setTotal(dto.getTotal());
        venta.setObservaciones(dto.getObservaciones());
        return venta;
    }
    
    private DetalleVentaDTO convertDetalleToDTO(DetalleVenta detalle) {
        DetalleVentaDTO dto = new DetalleVentaDTO();
        dto.setId(detalle.getId());
        dto.setVentaId(detalle.getVentaId());
        dto.setMedicamentoId(detalle.getMedicamentoId());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setSubtotal(detalle.getSubtotal());
        return dto;
    }
    
    private DetalleVenta convertDetalleToEntity(DetalleVentaDTO dto) {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setVentaId(dto.getVentaId());
        detalle.setMedicamentoId(dto.getMedicamentoId());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setSubtotal(dto.getSubtotal());
        return detalle;
    }
}


