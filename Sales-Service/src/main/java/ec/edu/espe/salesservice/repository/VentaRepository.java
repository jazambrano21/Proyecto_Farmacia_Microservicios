package ec.edu.espe.salesservice.repository;

import ec.edu.espe.salesservice.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    
    List<Venta> findBySucursalId(Long sucursalId);
    
    List<Venta> findByClienteId(Long clienteId);
    
    List<Venta> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    @Query("SELECT v FROM Venta v WHERE v.sucursalId = :sucursalId AND v.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Venta> findBySucursalIdAndFechaBetween(
            @Param("sucursalId") Long sucursalId,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);
}






