package com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.mapper;

import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.model.TareaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-06T21:28:46-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (Oracle Corporation)"
)
@Component
public class TareaMapperImpl implements TareaMapper {

    @Override
    public TareaDTO mapToEntitiesToModel(TareaEntity tareaEntity) {
        if ( tareaEntity == null ) {
            return null;
        }

        TareaDTO.TareaDTOBuilder tareaDTO = TareaDTO.builder();

        tareaDTO.id( tareaEntity.getId() );
        tareaDTO.titulo( tareaEntity.getTitulo() );
        tareaDTO.descripcion( tareaEntity.getDescripcion() );
        tareaDTO.estado( tareaEntity.getEstado() );
        tareaDTO.fecha_creacion( tareaEntity.getFecha_creacion() );

        return tareaDTO.build();
    }

    @Override
    public TareaEntity mapToModelToEntities(TareaDTO tareaDTO) {
        if ( tareaDTO == null ) {
            return null;
        }

        TareaEntity tareaEntity = new TareaEntity();

        tareaEntity.setId( tareaDTO.getId() );
        tareaEntity.setTitulo( tareaDTO.getTitulo() );
        tareaEntity.setDescripcion( tareaDTO.getDescripcion() );
        tareaEntity.setEstado( tareaDTO.getEstado() );
        tareaEntity.setFecha_creacion( tareaDTO.getFecha_creacion() );

        return tareaEntity;
    }
}
