package com.petcare360.service.impl;

import com.petcare360.model.Cita;
import com.petcare360.model.Mascota;
import com.petcare360.model.Veterinario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CitaServiceImplTest {

    private CitaServiceImpl citaService;
    private Mascota mascota;
    private Veterinario veterinario;

    @BeforeEach
    void setUp() {
        citaService = new CitaServiceImpl();

        mascota = new Mascota(1L, "Luna", "Perro", 3);
        veterinario = new Veterinario(1L, "Dr. Pérez", "General");
    }

    @Test
    void debeAgendarUnaCitaCorrectamente() {
        Cita cita = new Cita(null, mascota, veterinario, LocalDateTime.now().plusDays(1), "Vacunación");
        Cita creada = citaService.agendarCita(cita);

        assertNotNull(creada.getId());
        assertEquals(1, citaService.listarCitasPorVeterinario(veterinario.getId()).size());
    }

    @Test
    void debeLanzarErrorSiVeterinarioTieneCitaMismaHora() {
        LocalDateTime fecha = LocalDateTime.now().plusDays(1);

        Cita c1 = new Cita(null, mascota, veterinario, fecha, "Control general");
        Cita c2 = new Cita(null, new Mascota(2L, "Michi", "Gato", 2), veterinario, fecha, "Vacunación");

        citaService.agendarCita(c1);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> citaService.agendarCita(c2)
        );

        assertEquals("El veterinario ya tiene una cita en esa hora.", ex.getMessage());
    }

    @Test
    void debeObtenerCitaPorId() {
        Cita cita = new Cita(null, mascota, veterinario, LocalDateTime.now().plusDays(2), "Chequeo");
        Cita creada = citaService.agendarCita(cita);

        Cita encontrada = citaService.obtenerCita(creada.getId());
        assertEquals(creada.getId(), encontrada.getId());
    }

    @Test
    void debeLanzarErrorSiCitaNoExiste() {
        assertThrows(NoSuchElementException.class, () -> citaService.obtenerCita(999L));
    }

    @Test
    void debeCancelarCita() {
        Cita cita = new Cita(null, mascota, veterinario, LocalDateTime.now().plusDays(3), "Desparasitación");
        Cita creada = citaService.agendarCita(cita);

        citaService.cancelarCita(creada.getId());
        assertTrue(citaService.listarCitasPorVeterinario(veterinario.getId()).isEmpty());
    }

    @Test
    void debeListarCitasPorMascota() {
        citaService.agendarCita(new Cita(null, mascota, veterinario, LocalDateTime.now().plusDays(1), "Vacuna A"));
        citaService.agendarCita(new Cita(null, mascota, veterinario, LocalDateTime.now().plusDays(2), "Vacuna B"));

        List<Cita> citasMascota = citaService.listarCitasPorMascota(mascota.getId());
        assertEquals(2, citasMascota.size());
    }
}
