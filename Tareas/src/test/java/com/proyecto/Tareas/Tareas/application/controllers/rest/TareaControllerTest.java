package com.proyecto.Tareas.Tareas.application.controllers.rest;

import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;
import com.proyecto.Tareas.Tareas.domain.services.TareaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TareaControllerTest {

    @Mock
    private TareaService tareaService;

    @InjectMocks
    private TareaController tareaController;

    private TareaDTO tareaDTO;

    @BeforeEach
    public void setUp() {
        tareaDTO = new TareaDTO();
        tareaDTO.setId(1);
        tareaDTO.setTitulo("Test Tarea");
        tareaDTO.setEstado("PENDIENTE");
    }

    @Test
    public void testGetTareas() {
        when(tareaService.getTareas()).thenReturn(Collections.singletonList(tareaDTO));

        var response = tareaController.getTareas();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Test Tarea", response.get(0).getTitulo());
    }

    @Test
    public void testCrearTarea() {
        when(tareaService.crearTarea(any(TareaDTO.class))).thenReturn(ResponseEntity.ok().body(createSuccessResponse()));

        var response = tareaController.crearTarea(tareaDTO);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("Se guardo correctamente", response.getBody().get("message"));
    }

    @Test
    public void testActualizarTarea() {
        when(tareaService.actualizar(any(TareaDTO.class))).thenReturn(ResponseEntity.ok().body(createSuccessResponse()));

        var response = tareaController.actualizarTarea(tareaDTO);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("Se guardo correctamente", response.getBody().get("message"));
    }

    @Test
    public void testEliminarTarea() {
        when(tareaService.eliminar(anyInt())).thenReturn(ResponseEntity.ok().body(createSuccessResponse()));

        var response = tareaController.eliminarTarea(1);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().containsKey("message"));
    }

    // Métodos auxiliares
    private Map<String, Object> createSuccessResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Se guardo correctamente");
        response.put("data", tareaDTO);
        return response;
    }
}
