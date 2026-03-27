package org.example.application.UseCases;

import org.example.application.CitaRepositoryPort;
import org.example.application.DoctorRepositoryPort;
import org.example.domain.model.Cita;
import org.example.domain.model.Doctor;
import org.example.domain.rules.ReglaHorarioLaboral;
import org.example.domain.rules.ReglaSinEmpalmes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class AgendarCitaUseCase {

    private final ReglaHorarioLaboral reglaHorario;
    private final CitaRepositoryPort citaRepository;
    private final DoctorRepositoryPort doctorRepository;

    public AgendarCitaUseCase(ReglaHorarioLaboral reglaHorario, CitaRepositoryPort citaRepository, DoctorRepositoryPort doctorRepository) {
        this.citaRepository = citaRepository;
        this.reglaHorario = reglaHorario;
        this.doctorRepository = doctorRepository;
    }

    public Cita ejecutar(Cita cita) {
        LocalTime hora = cita.getFechaHora().toLocalTime();
        reglaHorario.validar(hora);

        Doctor doctor = doctorRepository.buscarPorId(cita.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado con ID: " + cita.getDoctorId()));

        if (!doctor.tieneEspecialidad(cita.getEspecialidad())) {
            throw new IllegalArgumentException("El doctor " + doctor.getNombre() +
                    " no atiende la especialidad solicitada (" + cita.getEspecialidad() + ").");
        }

        LocalDateTime finCita = cita.getFechaHoraFin();
        doctor.validarDisponibilidad(cita.getFechaHora(), finCita);

        List<Cita> citasDelDia = citaRepository.obtenerPorFecha(cita.getFechaHora().toLocalDate());

        List<Cita> citasDeLosRecursos = citasDelDia.stream()
                .filter(c -> c.getDoctorId().equals(cita.getDoctorId()) ||
                        (cita.getRecursoFisicoId() != null && cita.getRecursoFisicoId().equals(c.getRecursoFisicoId())))
                .collect(Collectors.toList());

        new ReglaSinEmpalmes().validar(cita, citasDeLosRecursos);

        Cita citaConEstado = new Cita(
                cita.getPaciente(),
                cita.getFechaHora(),
                cita.getEspecialidad(),
                cita.getDoctorId(),
                cita.getRecursoFisicoId()
        );

        return citaRepository.guardar(citaConEstado);
    }
    public Cita obtenerPorId(UUID id) {
        return citaRepository.obtenerPorId(id);
    }


}
