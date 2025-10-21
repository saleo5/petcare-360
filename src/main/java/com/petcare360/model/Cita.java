package com.petcare360.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
    private Long id;
    private Mascota mascota;
    private Veterinario veterinario;
    private LocalDateTime fechaHora;
    private String motivo;
}
