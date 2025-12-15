package ec.edu.espe.customerservice.repository;

import ec.edu.espe.customerservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByCedula(String cedula);
    
    boolean existsByCedula(String cedula);
}


