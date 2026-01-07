package ec.edu.espe.inventoryservice.repository;

import ec.edu.espe.inventoryservice.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    
    List<Sucursal> findByCiudad(String ciudad);
    
    @Query("SELECT s FROM Sucursal s WHERE s.nombre LIKE %:nombre%")
    List<Sucursal> findByNombreContaining(@Param("nombre") String nombre);
}






