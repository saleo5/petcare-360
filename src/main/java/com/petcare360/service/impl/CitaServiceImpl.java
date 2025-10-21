package com.petcare360.service.impl;

import com.petcare360.model.Cita;
import com.petcare360.service.CitaService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    private final List<Cita> citas = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Cita agendarCita(Cita cita) {
        boolean conflicto = citas.stream()
                .anyMatch(c -> c.getVeterinario().getId().equals(cita.getVeterinario().getId())
                        && c.getFechaHora().equals(cita.getFechaHora()));

        if (conflicto) {
            throw new IllegalArgumentException("El veterinario ya tiene una cita en esa hora.");
        }

        cita.setId(idGenerator.getAndIncrement());
        citas.add(cita);
        return cita;
    }

    @Override
    public Cita obtenerCita(Long id) {
        return citas.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Cita no encontrada."));
    }

    @Override
    public void cancelarCita(Long id) {
        citas.removeIf(c -> c.getId().equals(id));
    }

    @Override
    public List<Cita> listarCitasPorVeterinario(Long veterinarioId) {
        return citas.stream()
                .filter(c -> c.getVeterinario().getId().equals(veterinarioId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> listarCitasPorMascota(Long mascotaId) {
        return citas.stream()
                .filter(c -> c.getMascota().getId().equals(mascotaId))
                .collect(Collectors.toList());
    }
}
