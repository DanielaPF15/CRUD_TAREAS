package com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.gateway;

import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.TareaRepository;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.mapper.TareaMapper;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.model.TareaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TareaGatewayImplTest {

    @Mock
    private TareaRepository repository;

    @Mock
    private TareaMapper mapper;

    @InjectMocks
    private TareaGatewayImpl tareaGateway;

    private TareaDTO tareaDTO;
    private TareaEntity tareaEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tareaDTO = new TareaDTO();
        tareaDTO.setId(1);
        tareaDTO.setTitulo("Test Tarea");

        tareaEntity = new TareaEntity();
        tareaEntity.setId(1);
        tareaEntity.setTitulo("Test Tarea");
    }

    @Test
    void testSave() {
        when(mapper.mapToModelToEntities(any(TareaDTO.class))).thenReturn(tareaEntity);
        when(repository.save(any(TareaEntity.class))).thenReturn(tareaEntity);
        when(mapper.mapToEntitiesToModel(any(TareaEntity.class))).thenReturn(tareaDTO);

        TareaDTO result = tareaGateway.save(tareaDTO);

        assertNotNull(result);
        assertEquals(tareaDTO.getId(), result.getId());
        verify(repository, times(1)).save(any(TareaEntity.class));
    }

    @Test
    void testUpdate() {
        when(mapper.mapToModelToEntities(any(TareaDTO.class))).thenReturn(tareaEntity);
        when(repository.save(any(TareaEntity.class))).thenReturn(tareaEntity);
        when(mapper.mapToEntitiesToModel(any(TareaEntity.class))).thenReturn(tareaDTO);

        TareaDTO result = tareaGateway.update(tareaDTO);

        assertNotNull(result);
        assertEquals(tareaDTO.getId(), result.getId());
        verify(repository, times(1)).save(any(TareaEntity.class));
    }

    @Test
    void testFindByTareaId_Found() {
        when(repository.findByIdTarea(anyInt())).thenReturn(Optional.of(tareaEntity));
        when(mapper.mapToEntitiesToModel(any(TareaEntity.class))).thenReturn(tareaDTO);

        TareaDTO result = tareaGateway.findByTareaId(1);

        assertNotNull(result);
        assertEquals(tareaDTO.getId(), result.getId());
    }

    @Test
    void testFindByTareaId_NotFound() {
        when(repository.findByIdTarea(anyInt())).thenReturn(Optional.empty());

        TareaDTO result = tareaGateway.findByTareaId(1);

        assertNull(result);
    }

    @Test
    void testDelete() {
        when(repository.findByIdTarea(anyInt())).thenReturn(Optional.of(tareaEntity));

        tareaGateway.delete(1);

        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void testGetTareas() {
        when(repository.findAll()).thenReturn(Arrays.asList(tareaEntity));
        when(mapper.mapToEntitiesToModel(any(TareaEntity.class))).thenReturn(tareaDTO);

        List<TareaDTO> result = tareaGateway.getTareas();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByTareaTitulo_Found() {
        when(repository.findByTituloTarea(anyString())).thenReturn(Optional.of(tareaEntity));
        when(mapper.mapToEntitiesToModel(any(TareaEntity.class))).thenReturn(tareaDTO);

        TareaDTO result = tareaGateway.findByTareaTitulo("Test Tarea");

        assertNotNull(result);
        assertEquals(tareaDTO.getTitulo(), result.getTitulo());
    }

    @Test
    void testFindByTareaTitulo_NotFound() {
        when(repository.findByTituloTarea(anyString())).thenReturn(Optional.empty());

        TareaDTO result = tareaGateway.findByTareaTitulo("Nonexistent Tarea");

        assertNull(result);
    }
}
