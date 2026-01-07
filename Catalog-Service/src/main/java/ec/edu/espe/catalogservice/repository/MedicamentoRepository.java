package ec.edu.espe.catalogservice.repository;

import ec.edu.espe.catalogservice.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    
    List<Medicamento> findByCategoria(String categoria);
    
    List<Medicamento> findByRequierePrescripcion(Boolean requierePrescripcion);
    
    Optional<Medicamento> findByCodigoBarras(String codigoBarras);
    
    @Query("SELECT m FROM Medicamento m WHERE m.nombre LIKE %:nombre%")
    List<Medicamento> findByNombreContaining(@Param("nombre") String nombre);
    
    @Query("SELECT m FROM Medicamento m WHERE m.precio BETWEEN :precioMin AND :precioMax")
    List<Medicamento> findByPrecioBetween(@Param("precioMin") java.math.BigDecimal precioMin, 
                                          @Param("precioMax") java.math.BigDecimal precioMax);
}






