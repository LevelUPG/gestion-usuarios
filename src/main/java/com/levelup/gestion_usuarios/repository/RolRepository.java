package com.levelup.gestion_usuarios.repository;

import com.levelup.gestion_usuarios.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {
    
    Optional<RolEntity> findByNombre(String nombre);
    
    Boolean existsByNombre(String nombre);
    
    // Query nativa para obtener roles con cantidad de usuarios
    @Query(value = "SELECT r.*, COUNT(ur.usuario_id) as cantidad_usuarios " +
                   "FROM roles r " +
                   "LEFT JOIN usuario_roles ur ON r.id = ur.rol_id " +
                   "GROUP BY r.id", nativeQuery = true)
    List<Object[]> obtenerRolesConCantidadUsuarios();
}
