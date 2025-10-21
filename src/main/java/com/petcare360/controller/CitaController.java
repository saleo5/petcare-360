package com.petcare360.controller;

import com.petcare360.model.Cita;
import com.petcare360.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @PostMapping("/citas")
    public ResponseEntity<Cita> agendarCita(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.agendarCita(cita));
    }

    @GetMapping("/citas/{id}")
    public ResponseEntity<Cita> obtenerCita(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.obtenerCita(id));
    }

    @DeleteMapping("/citas/{id}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/veterinarios/{id}/citas")
    public ResponseEntity<List<Cita>> listarPorVeterinario(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.listarCitasPorVeterinario(id));
    }

    @GetMapping("/mascotas/{id}/citas")
    public ResponseEntity<List<Cita>> listarPorMascota(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.listarCitasPorMascota(id));
    }
}
