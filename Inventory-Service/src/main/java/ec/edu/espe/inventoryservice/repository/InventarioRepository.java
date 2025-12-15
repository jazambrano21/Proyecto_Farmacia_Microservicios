package ec.edu.espe.inventoryservice.repository;

import ec.edu.espe.inventoryservice.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    
    List<Inventario> findBySucursalId(Long sucursalId);
    
    List<Inventario> findByMedicamentoId(Long medicamentoId);
    
    Optional<Inventario> findBySucursalIdAndMedicamentoId(Long sucursalId, Long medicamentoId);
    
    @Query("SELECT i FROM Inventario i WHERE i.sucursalId = :sucursalId AND i.cantidad <= i.stockMinimo")
    List<Inventario> findStockBajoBySucursalId(@Param("sucursalId") Long sucursalId);
    
    @Query("SELECT i FROM Inventario i WHERE i.cantidad <= i.stockMinimo")
    List<Inventario> findAllStockBajo();
}


