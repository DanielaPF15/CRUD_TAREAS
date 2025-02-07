package com.proyecto.Tareas.Tareas.application.controllers.rest;

import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;
import com.proyecto.Tareas.Tareas.domain.services.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("tareas")
@AllArgsConstructor
@Tag(name = "Tareas", description = "Crud de Tareas")
public class TareaController {

    @Autowired
    private final TareaService tareaService;

    @Operation(description = "Consultar tareas")
    @GetMapping("/")
    public List<TareaDTO> getTareas() {
        return tareaService.getTareas();
    }

    @Operation(description = "Crear tarea")
    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearTarea(@RequestBody TareaDTO nuevaTarea) {
        return tareaService.crearTarea(nuevaTarea);
    }

    @Operation(description = "Editar tarea")
    @PutMapping("/editar")
    public ResponseEntity<Map<String, Object>> actualizarTarea(@RequestBody TareaDTO nuevaTarea) {
        return tareaService.actualizar(nuevaTarea);
    }

    @Operation(description = "Eliminar tarea")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminarTarea(@PathVariable Integer id) {
        return tareaService.eliminar(id);
    }
}
