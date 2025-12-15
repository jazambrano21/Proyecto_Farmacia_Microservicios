package ec.edu.espe.customerservice.service;

import ec.edu.espe.customerservice.dto.ClienteDTO;
import ec.edu.espe.customerservice.exception.ResourceNotFoundException;
import ec.edu.espe.customerservice.model.Cliente;
import ec.edu.espe.customerservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<ClienteDTO> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public ClienteDTO getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
        return convertToDTO(cliente);
    }
    
    public ClienteDTO getClienteByCedula(String cedula) {
        Cliente cliente = clienteRepository.findByCedula(cedula)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con cédula: " + cedula));
        return convertToDTO(cliente);
    }
    
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByCedula(clienteDTO.getCedula())) {
            throw new IllegalArgumentException("Ya existe un cliente con la cédula: " + clienteDTO.getCedula());
        }
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return convertToDTO(savedCliente);
    }
    
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
        
        // Verificar si la cédula ya existe en otro cliente
        if (!cliente.getCedula().equals(clienteDTO.getCedula()) && 
            clienteRepository.existsByCedula(clienteDTO.getCedula())) {
            throw new IllegalArgumentException("Ya existe un cliente con la cédula: " + clienteDTO.getCedula());
        }
        
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setCedula(clienteDTO.getCedula());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setDireccion(clienteDTO.getDireccion());
        
        Cliente updatedCliente = clienteRepository.save(cliente);
        return convertToDTO(updatedCliente);
    }
    
    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
    
    private ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setCedula(cliente.getCedula());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setFechaRegistro(cliente.getFechaRegistro());
        return dto;
    }
    
    private Cliente convertToEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setCedula(dto.getCedula());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        return cliente;
    }
}


