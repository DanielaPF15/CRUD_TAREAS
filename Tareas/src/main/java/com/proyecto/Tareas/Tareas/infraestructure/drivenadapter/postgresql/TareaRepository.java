package com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql;


import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.model.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<TareaEntity,Integer> {
    @Query(value = "Select entity from TareaEntity entity where entity.id = ?1")
    Optional<TareaEntity> findByIdTarea(Integer id);

    @Query(value = "Select entity from TareaEntity entity where entity.titulo = ?1")
    Optional<TareaEntity> findByTituloTarea(String titulo);
}
