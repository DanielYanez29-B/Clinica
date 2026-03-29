package org.example.domain.services;

import org.example.application.CitaRepositoryPort;
import org.example.application.DoctorRepositoryPort;
import org.example.domain.model.Cita;
import org.example.domain.model.Doctor;
import org.example.domain.rules.ReglaHorarioLaboral;
import org.example.domain.rules.ReglaSinEmpalmes;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReglasDeAgenda {

    private final ReglaHorarioLaboral reglaHorario;
    private final DoctorRepositoryPort doctorRepository;
    private final CitaRepositoryPort citaRepository;

    public ReglasDeAgenda(ReglaHorarioLaboral reglaHorario, DoctorRepositoryPort doctorRepository, CitaRepositoryPort citaRepository) {
        this.reglaHorario = reglaHorario;
        this.doctorRepository = doctorRepository;
        this.citaRepository = citaRepository;
    }

    public void validarCitaViable(Cita cita) {
        validarHorarioLaboral(cita);
        validarDoctorExisteYTieneEspecialidad(cita);
        validarDisponibilidadDoctor(cita);
        validarSinEmpalmes(cita);
    }

    private void validarHorarioLaboral(Cita cita) {
        LocalTime hora = cita.getFechaHora().toLocalTime();
        reglaHorario.validar(hora);
    }

    private void validarDoctorExisteYTieneEspecialidad(Cita cita) {
        Doctor doctor = doctorRepository.buscarPorId(cita.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado con ID: " + cita.getDoctorId()));

        if (!doctor.tieneEspecialidad(cita.getEspecialidad())) {
            throw new IllegalArgumentException("El doctor " + doctor.getNombre() +
                    " no atiende la especialidad solicitada (" + cita.getEspecialidad() + ").");
        }
    }

    private void validarDisponibilidadDoctor(Cita cita) {
        Doctor doctor = doctorRepository.buscarPorId(cita.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado con ID: " + cita.getDoctorId()));
        doctor.validarDisponibilidad(cita.getFechaHora(), cita.getFechaHoraFin());
    }

    private void validarSinEmpalmes(Cita cita) {
        List<Cita> citasDelDia = citaRepository.obtenerPorFecha(cita.getFechaHora().toLocalDate());

        List<Cita> citasDeLosRecursos = citasDelDia.stream()
                .filter(c -> c.getDoctorId().equals(cita.getDoctorId()) ||
                        (cita.getRecursoFisicoId() != null &&
                                cita.getRecursoFisicoId().equals(c.getRecursoFisicoId())))
                .collect(Collectors.toList());
        new ReglaSinEmpalmes().validar(cita, citasDeLosRecursos);


    }


}
