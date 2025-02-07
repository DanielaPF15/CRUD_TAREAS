package com.proyecto.Tareas.Tareas.domain.gateway;

import com.proyecto.Tareas.Tareas.domain.model.TareaDTO;

import java.util.List;

public interface TareaGateway {

    TareaDTO save(TareaDTO tareaDTO);

    void delete(Integer id);

    TareaDTO update(TareaDTO tareaDTO);

    TareaDTO findByTareaId(Integer id);

    TareaDTO findByTareaTitulo(String titulo);

    List<TareaDTO> getTareas();
}
