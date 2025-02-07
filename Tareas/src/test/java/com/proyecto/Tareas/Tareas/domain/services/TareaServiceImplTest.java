package com.proyecto.Tareas.Tareas.domain.services;

import com.proyecto.Tareas.Tareas.domain.gateway.TareaGateway;
import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.mapper.TareaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TareaServiceImplTest {

    @Mock
    private TareaGateway tareaGateway;

    @Mock
    private TareaMapper tareaMapper;

    @InjectMocks
    private TareaServiceImpl tareaService;

    private TareaDTO tareaDTO;

    @BeforeEach
    public void setUp() {
        tareaDTO = new TareaDTO();
        tareaDTO.setId(1);
        tareaDTO.setTitulo("Test Tarea");
        tareaDTO.setEstado("PENDIENTE");
        tareaDTO.setFecha_creacion(LocalDate.now());
    }

    @Test
    public void testCrearTarea_Success() {
        when(tareaGateway.findByTareaTitulo(tareaDTO.getTitulo())).thenReturn(null);
        when(tareaGateway.save(any(TareaDTO.class))).thenReturn(tareaDTO);

        ResponseEntity<Map<String, Object>> response = tareaService.crearTarea(tareaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("Se guardo correctamente", response.getBody().get("message"));
        verify(tareaGateway, times(1)).save(any(TareaDTO.class));
    }

    @Test
    public void testCrearTarea_DuplicateTitle() {
        when(tareaGateway.findByTareaTitulo(tareaDTO.getTitulo())).thenReturn(tareaDTO);

        ResponseEntity<Map<String, Object>> response = tareaService.crearTarea(tareaDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("Ya existe una tarea con ese titulo", response.getBody().get("message"));
        verify(tareaGateway, never()).save(any(TareaDTO.class));
    }

    @Test
    public void testCrearTarea_EmptyTitle() {
        tareaDTO.setTitulo("");

        ResponseEntity<Map<String, Object>> response = tareaService.crearTarea(tareaDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("El titulo no puede ser vacio", response.getBody().get("message"));
        verify(tareaGateway, never()).save(any(TareaDTO.class));
    }

    @Test
    public void testGetTareas() {
        when(tareaGateway.getTareas()).thenReturn(Collections.singletonList(tareaDTO));

        var tareas = tareaService.getTareas();

        assertNotNull(tareas);
        assertEquals(1, tareas.size());
        assertEquals("Test Tarea", tareas.get(0).getTitulo());
        verify(tareaGateway, times(1)).getTareas();
    }

    @Test
    public void testEliminarTarea_Success() {
        when(tareaGateway.findByTareaId(1)).thenReturn(tareaDTO);
        doNothing().when(tareaGateway).delete(1);

        ResponseEntity<Map<String, Object>> response = tareaService.eliminar(1);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("Se elimino correctamente", response.getBody().get("message"));
        verify(tareaGateway, times(1)).delete(1);
    }

    @Test
    public void testEliminarTarea_NotFound() {
        when(tareaGateway.findByTareaId(1)).thenReturn(null);

        ResponseEntity<Map<String, Object>> response = tareaService.eliminar(1);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("No existe una tarea asociada al id", response.getBody().get("message"));
        verify(tareaGateway, never()).delete(1);
    }

    @Test
    public void testActualizarTarea_Success() {
        when(tareaGateway.findByTareaId(tareaDTO.getId())).thenReturn(tareaDTO);
        when(tareaGateway.update(any(TareaDTO.class))).thenReturn(tareaDTO);

        ResponseEntity<Map<String, Object>> response = tareaService.actualizar(tareaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("Se actualizo correctamente", response.getBody().get("message"));
        verify(tareaGateway, times(1)).update(any(TareaDTO.class));
    }



    @Test
    public void testActualizarTarea_NotFound() {
        when(tareaGateway.findByTareaId(tareaDTO.getId())).thenReturn(null);

        ResponseEntity<Map<String, Object>> response = tareaService.actualizar(tareaDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("No existe una tarea asociada al id", response.getBody().get("message"));
        verify(tareaGateway, never()).update(any(TareaDTO.class));
    }
}