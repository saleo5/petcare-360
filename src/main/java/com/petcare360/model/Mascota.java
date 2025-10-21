package com.petcare360.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {
    private Long id;
    private String nombre;
    private String tipo; // perro, gato, etc. proximamente creo podría aplicar un enum
    private int edad;
}
