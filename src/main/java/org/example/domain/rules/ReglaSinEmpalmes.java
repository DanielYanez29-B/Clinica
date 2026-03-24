package org.example.domain.rules;

import org.example.domain.model.Cita;

import java.util.List;

public class ReglaSinEmpalmes {

        public void validar(Cita citaNueva, List<Cita> citasExistentes) {
            for (Cita citaExistente : citasExistentes) {
                if (citaExistente.getFechaHora() == null || citaExistente.getFechaHoraFin() == null) {
                    continue;
                }

                if (citaNueva.getId() != null && citaNueva.getId().equals(citaExistente.getId())) {
                    continue;
                }
                boolean empiezaAntesQueTermineLaOtra = citaNueva.getFechaHora().isBefore(citaExistente.getFechaHoraFin());
                boolean terminaDespuesQueEmpieceLaOtra = citaNueva.getFechaHoraFin().isAfter(citaExistente.getFechaHora());

                if (empiezaAntesQueTermineLaOtra && terminaDespuesQueEmpieceLaOtra) {
                    throw new RuntimeException("Horario no disponible: La cita se empalma con otra reservada de "
                            + citaExistente.getFechaHora().toLocalTime() + " a "
                            + citaExistente.getFechaHoraFin().toLocalTime());
                }
            }

        }
}
