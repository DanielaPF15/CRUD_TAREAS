package com.proyecto.Tareas.Tareas.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaDTO {

    private Integer id;
    @NotNull
    private String titulo;

    private String descripcion;

    private String estado;

    private LocalDate fecha_creacion;

}
