package com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.mapper;


import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.model.TareaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TareaMapper {
    TareaDTO mapToEntitiesToModel(TareaEntity tareaEntity);

    TareaEntity mapToModelToEntities(TareaDTO tareaDTO);

}
