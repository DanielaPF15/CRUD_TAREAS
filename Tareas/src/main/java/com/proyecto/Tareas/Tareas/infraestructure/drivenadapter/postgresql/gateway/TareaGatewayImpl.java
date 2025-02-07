package com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.gateway;

import com.proyecto.Tareas.Tareas.domain.gateway.TareaGateway;
import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.TareaRepository;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.mapper.TareaMapper;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.model.TareaEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TareaGatewayImpl implements TareaGateway {
    private TareaRepository repository;
    private TareaMapper mapper;

    @Override
    public TareaDTO save(TareaDTO tareaDTO) {
        TareaEntity tareaEntity = repository.save(mapper.mapToModelToEntities(tareaDTO));
        return mapper.mapToEntitiesToModel(tareaEntity);
    }

    @Override
    public TareaDTO update(TareaDTO TareaDTO) {
        TareaEntity entity = mapper.mapToModelToEntities(TareaDTO);
        TareaEntity saved = repository.save(entity);
        return mapper.mapToEntitiesToModel(saved);
    }

    @Override
    public TareaDTO findByTareaId(Integer idTarjeta) {
        Optional<TareaEntity> optionalTareaEntity = repository.findByIdTarea(idTarjeta);
        if (optionalTareaEntity.isPresent()) {
            TareaEntity TareaEntity = optionalTareaEntity.get();
            return mapper.mapToEntitiesToModel(TareaEntity);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Integer idTarjeta) {
        Optional<TareaEntity> optionalTareaEntity = repository.findByIdTarea(idTarjeta);
        if (optionalTareaEntity.isPresent()) {
            TareaEntity tareaEntity = optionalTareaEntity.get();
            repository.deleteById(tareaEntity.getId());
        }
    }

    public List<TareaDTO> getTareas() {
        List<TareaEntity> tareasEntity = repository.findAll();
        List<TareaDTO> tareafins = new ArrayList<>();
        tareasEntity.forEach(tareaEntity -> {
            TareaDTO tareaDTO = mapper.mapToEntitiesToModel(tareaEntity);
            tareafins.add(tareaDTO);
        });
        return tareafins;
    }

    @Override
    public TareaDTO findByTareaTitulo(String titulo) {
        Optional<TareaEntity> optionalTareaEntity = repository.findByTituloTarea(titulo);
        if (optionalTareaEntity.isPresent()) {
            TareaEntity TareaEntity = optionalTareaEntity.get();
            return mapper.mapToEntitiesToModel(TareaEntity);
        } else {
            return null;
        }
    }
}
