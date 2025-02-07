package com.proyecto.Tareas.Tareas.domain.services;

import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface TareaService {
    ResponseEntity<Map<String, Object>> crearTarea(TareaDTO tareaDTO);

    List<TareaDTO> getTareas();

    ResponseEntity<Map<String, Object>> eliminar(Integer id);

    ResponseEntity<Map<String, Object>> actualizar(TareaDTO tareaDTO);
}
