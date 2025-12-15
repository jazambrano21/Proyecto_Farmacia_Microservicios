package ec.edu.espe.salesservice.repository;

import ec.edu.espe.salesservice.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    
    List<DetalleVenta> findByVentaId(Long ventaId);
    
    List<DetalleVenta> findByMedicamentoId(Long medicamentoId);
}


