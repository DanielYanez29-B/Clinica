package org.example.domain.rules;

import java.time.LocalTime;

public class ReglaHorarioLaboral {
    private final LocalTime apertura;
    private final LocalTime cierre;

    public ReglaHorarioLaboral(LocalTime apertura, LocalTime cierre) {
        this.apertura = apertura;
        this.cierre = cierre;
    }

    public void validar(LocalTime horaCita){
        if (horaCita.isBefore(apertura)) {
            throw new RuntimeException("La clínica aun está cerrada, Abrimos a las " + apertura);
        }
        if (horaCita.isAfter(cierre)) {
            throw new RuntimeException("La clinica ya esta cerrada. Cerramos a las " + cierre);

        }
        if (!horaCita.isBefore(LocalTime.of(14, 0)) && horaCita.isBefore(LocalTime.of(16, 0))) {
            throw new RuntimeException("La clínica está cerrada por horario de comida (14:00 - 16:00).");
        }
    }
}