package com.petcare360.service;

import com.petcare360.model.Cita;
import java.util.List;

public interface CitaService {
    Cita agendarCita(Cita cita);
    Cita obtenerCita(Long id);
    void cancelarCita(Long id);
    List<Cita> listarCitasPorVeterinario(Long veterinarioId);
    List<Cita> listarCitasPorMascota(Long mascotaId);
}
