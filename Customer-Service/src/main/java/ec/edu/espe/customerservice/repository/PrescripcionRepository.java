package ec.edu.espe.customerservice.repository;

import ec.edu.espe.customerservice.model.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescripcionRepository extends JpaRepository<Prescripcion, Long> {
    
    List<Prescripcion> findByClienteId(Long clienteId);
    
    List<Prescripcion> findByClienteIdAndActiva(Long clienteId, Boolean activa);
    
    List<Prescripcion> findByMedicamentoId(Long medicamentoId);
    
    @Query("SELECT p FROM Prescripcion p WHERE p.clienteId = :clienteId AND p.medicamentoId = :medicamentoId AND p.activa = true")
    List<Prescripcion> findActivePrescripcionByClienteAndMedicamento(
            @Param("clienteId") Long clienteId, 
            @Param("medicamentoId") Long medicamentoId);
}


