package org.example.domain.model.valueobjects;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VentanaDisponibilidad {
    private final DayOfWeek diaSemana;
    private final LocalTime horaInicioTurno;
    private final LocalTime horaFinTurno;

    public VentanaDisponibilidad(DayOfWeek diaSemana, LocalTime horaInicioTurno, LocalTime horaFinTurno) {
        if (horaInicioTurno.isAfter(horaFinTurno)) {
            throw new IllegalArgumentException("La hora de inicio del turno no puede ser después de la hora de fin del turno.");
        }
        this.diaSemana = diaSemana;
        this.horaInicioTurno = horaInicioTurno;
        this.horaFinTurno = horaFinTurno;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicioTurno;
    }

    public LocalTime getHoraFin() {
        return horaFinTurno;
    }

    public boolean cubreElhorarioDe(LocalDateTime inicioCita, LocalDateTime finCita) {
        if (inicioCita.getDayOfWeek() != diaSemana) {
            return false;
        }
        LocalTime horaInicioCita = inicioCita.toLocalTime();
        LocalTime horaFinCita = finCita.toLocalTime();
        return !horaInicioCita.isBefore(horaInicioTurno) && !horaFinCita.isAfter(horaFinTurno);
    }
}


