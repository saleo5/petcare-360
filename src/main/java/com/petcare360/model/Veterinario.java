package com.petcare360.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veterinario {
    private Long id;
    private String nombre;
    private String especialidad;
}
