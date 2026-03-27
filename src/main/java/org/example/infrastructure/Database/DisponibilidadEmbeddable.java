package org.example.infrastructure.Database;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
public class DisponibilidadEmbeddable {

    @Enumerated(EnumType.STRING)
    private DayOfWeek diaSemana;

    private LocalTime horaInicioTurno;
    private LocalTime horaFinTurno;

    public DisponibilidadEmbeddable() {}

    public DisponibilidadEmbeddable(DayOfWeek diaSemana, LocalTime horaInicioTurno, LocalTime horaFinTurno) {
        this.diaSemana = diaSemana;
        this.horaInicioTurno = horaInicioTurno;
        this.horaFinTurno = horaFinTurno;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicioTurno() {
        return horaInicioTurno;
    }

    public void setHoraInicioTurno(LocalTime horaInicioTurno) {
        this.horaInicioTurno = horaInicioTurno;
    }

    public LocalTime getHoraFinTurno() {
        return horaFinTurno;
    }

    public void setHoraFinTurno(LocalTime horaFinTurno) {
        this.horaFinTurno = horaFinTurno;
    }
}
