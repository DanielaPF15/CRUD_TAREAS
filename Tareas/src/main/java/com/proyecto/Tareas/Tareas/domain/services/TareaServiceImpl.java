package com.proyecto.Tareas.Tareas.domain.services;

import com.proyecto.Tareas.Tareas.domain.gateway.TareaGateway;
import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;
import com.proyecto.Tareas.Tareas.infraestructure.drivenadapter.postgresql.mapper.TareaMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TareaServiceImpl implements TareaService {

    private static final String ERROR_KEY = "error";
    private static final String MESSAGE_KEY = "message";
    private static final String DATA_KEY = "data";
    private static final String ID_KEY = "Id";

    private TareaGateway tareaGateway;
    private TareaMapper mapper;

    @Override
    public ResponseEntity<Map<String, Object>> crearTarea(TareaDTO tareaDTO) {
        return Optional.ofNullable(tareaGateway.findByTareaTitulo(tareaDTO.getTitulo()))
                .map(dto -> createErrorResponse("Ya existe una tarea con ese titulo", HttpStatus.CONFLICT))
                .orElseGet(() -> {
                    if (tareaDTO.getTitulo() == null || tareaDTO.getTitulo().isEmpty()) {
                        return createErrorResponse("El titulo no puede ser vacio", HttpStatus.CONFLICT);
                    }
                    tareaDTO.setFecha_creacion(LocalDate.now());
                    tareaGateway.save(tareaDTO);
                    return createSuccessResponse(tareaDTO, "Se guardo correctamente", HttpStatus.CREATED);
                });
    }

    @Override
    public List<TareaDTO> getTareas() {
        return tareaGateway.getTareas();
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminar(Integer id) {
        TareaDTO tareaExistente = tareaGateway.findByTareaId(id);
        if (tareaExistente == null) {
            return createErrorResponse("No existe una tarea asociada al id", HttpStatus.CONFLICT);
        }
        tareaGateway.delete(id);
        return createSuccessResponse(id, "Se elimino correctamente", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> actualizar(TareaDTO tareaDTO) {
        TareaDTO tareaExistente = tareaGateway.findByTareaId(tareaDTO.getId());
        if (tareaExistente == null) {
            return createErrorResponse("No existe una tarea asociada al id", HttpStatus.CONFLICT);
        }
        tareaGateway.update(tareaDTO);
        return createSuccessResponse(tareaDTO, "Se actualizo correctamente", HttpStatus.CREATED);
    }

    private ResponseEntity<Map<String, Object>> createErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR_KEY, true);
        response.put(MESSAGE_KEY, message);
        return new ResponseEntity<>(response, status);
    }

    private ResponseEntity<Map<String, Object>> createSuccessResponse(Object data, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put(DATA_KEY, data);
        response.put(MESSAGE_KEY, message);
        return new ResponseEntity<>(response, status);
    }
}
